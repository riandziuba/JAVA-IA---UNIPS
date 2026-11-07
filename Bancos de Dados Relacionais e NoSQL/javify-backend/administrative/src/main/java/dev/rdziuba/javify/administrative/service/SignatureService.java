package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.Signature;
import dev.rdziuba.javify.administrative.dto.SignatureRequest;
import dev.rdziuba.javify.administrative.dto.SignatureResponse;
import dev.rdziuba.javify.administrative.dto.PlanResponse;
import dev.rdziuba.javify.administrative.repository.SignatureRepository;
import dev.rdziuba.javify.administrative.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SignatureService {

    private final SignatureRepository signatureRepository;
    private final PlanRepository planRepository;

    public SignatureService(SignatureRepository signatureRepository, PlanRepository planRepository) {
        this.signatureRepository = signatureRepository;
        this.planRepository = planRepository;
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
}
