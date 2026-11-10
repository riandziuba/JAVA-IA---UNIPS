package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.PaymentLog;
import dev.rdziuba.javify.administrative.repository.PaymentLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentLogService {

    private final PaymentLogRepository paymentLogRepository;

    public PaymentLogService(PaymentLogRepository paymentLogRepository) {
        this.paymentLogRepository = paymentLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailure(Integer signatureId, String reason) {
        var paymentLog = new PaymentLog();

        paymentLog.setSignatureId(signatureId);
        paymentLog.setReason(reason);
        paymentLog.setOccurredAt(LocalDateTime.now());

        paymentLogRepository.save(paymentLog);

        System.out.println("Failure log saved for signature " + signatureId);
    }

}
