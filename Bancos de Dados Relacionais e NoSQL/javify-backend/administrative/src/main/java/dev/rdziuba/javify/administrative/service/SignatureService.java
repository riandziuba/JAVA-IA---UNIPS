package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.Signature;
import dev.rdziuba.javify.administrative.dto.CancellationResponse;
import dev.rdziuba.javify.administrative.dto.SignatureRequest;
import dev.rdziuba.javify.administrative.dto.SignatureResponse;
import dev.rdziuba.javify.administrative.dto.PlanResponse;
import dev.rdziuba.javify.administrative.repository.SignatureRepository;
import dev.rdziuba.javify.administrative.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SignatureService {

    private final SignatureRepository signatureRepository;
    private final PlanRepository planRepository;
    private final PaymentLogService paymentLogService;

    public SignatureService(SignatureRepository signatureRepository,
                            PlanRepository planRepository,
                            PaymentLogService paymentLogService) {
        this.signatureRepository = signatureRepository;
        this.planRepository = planRepository;
        this.paymentLogService = paymentLogService;
    }

    public List<SignatureResponse> findAll() {
        return signatureRepository.findAllWithPlan().stream()
                .map(this::toResponse)
                .toList();
    }

    public SignatureResponse findById(Integer id) {
        return signatureRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + id));
    }

    @Transactional
    public SignatureResponse create(SignatureRequest request) {
        var plan = planRepository.findById(request.planId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + request.planId()));
        var signature = new Signature();
        signature.setPlan(plan);
        signature.setActive(request.active());
        return toResponse(signatureRepository.save(signature));
    }

    @Transactional
    public SignatureResponse update(Integer id, SignatureRequest request) {
        var signature = signatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + id));
        var plan = planRepository.findById(request.planId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + request.planId()));
        signature.setPlan(plan);
        signature.setActive(request.active());
        return toResponse(signatureRepository.save(signature));
    }

    @Transactional
    public void delete(Integer id) {
        if (!signatureRepository.existsById(id)) {
            throw new EntityNotFoundException("Signature not found: " + id);
        }
        signatureRepository.deleteById(id);
    }

    private SignatureResponse toResponse(Signature signature) {
        var plan = signature.getPlan();
        var planResponse = new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getPrice(),
                plan.isHasAdvertising(),
                plan.getMembersLimit(),
                plan.isOfflineMode()
        );
        return new SignatureResponse(
                signature.getId(),
                planResponse,
                signature.isActive()
        );
    }

    @Transactional
    public void cancel(Integer id) {
        System.out.println("1 - Entered cancel method: " + id);

        System.out.println("2 - Trying to acquire the lock...");

        Signature signature = signatureRepository
                .findByIdForUpdate(id)
                .orElseThrow(() -> new EntityNotFoundException("Signature not found: " + id));

        System.out.println("3 - Lock acquired");

        if (!signature.isActive()) {
            System.out.println("4 - Signature was already canceled");
            throw new IllegalStateException("Signature is already canceled: " + id);
        }

        System.out.println("5 - Starting pause");

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Operation interrupted", e);
        }

        System.out.println("6 - Changing status");
        signature.setActive(false);
    }

    @Transactional
    public List<Integer> processBatch(int limit, int seconds) {
        String worker = Thread.currentThread().getName();
        Pageable pageable = PageRequest.of(0, limit);

        System.out.println(worker + " - looking for signatures");

        List<Signature> signatures =
                signatureRepository.findBatchForProcessing(pageable);

        List<Integer> ids = signatures.stream()
                .map(Signature::getId)
                .toList();

        System.out.println(worker + " - locked records: " + ids);

        try {
            Thread.sleep(seconds * 1_000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Processing interrupted", e);
        }

        System.out.println(worker + " - finishing records: " + ids);

        return ids;
    }

    @Transactional
    public void updateStatusWithOptimisticLock(
            Integer id,
            boolean active,
            int seconds
    ) {
        String request = Thread.currentThread().getName();

        Signature signature = signatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Signature not found: " + id
                ));

        System.out.printf(
                "%s read: id=%d, active=%s, version=%d%n",
                request,
                signature.getId(),
                signature.isActive(),
                signature.getVersion()
        );

        try {
            Thread.sleep(seconds * 1_000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Operation interrupted", e);
        }

        signature.setActive(active);

        System.out.printf(
                "%s trying to save: active=%s, expected version=%d%n",
                request,
                active,
                signature.getVersion()
        );

        signatureRepository.flush();

        System.out.printf(
                "%s saved successfully; new version=%d%n",
                request,
                signature.getVersion()
        );
    }

    @Transactional(rollbackFor = {IOException.class})
    public void confirmPayment(
            Integer signatureId,
            boolean paymentApproved
    ) throws IOException {
        var signature = signatureRepository
                .findById(signatureId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Signature not found: " + signatureId
                ));

        signature.setActive(true);

        if (!paymentApproved) {
            paymentLogService.logFailure(
                    signatureId,
                    "Pagamento recusado"
            );

            throw new IOException("Payment declined");
        }
    }

    @Transactional
    public CancellationResponse cancelWithRefund(Integer signatureId) {
        BigDecimal refund = signatureRepository.calculateRefund(signatureId);

        Signature signature = signatureRepository.findById(signatureId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Signature not found: " + signatureId));
        signature.setActive(false);

        return new CancellationResponse(signatureId, refund);
    }
}
