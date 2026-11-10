package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Integer> {
}
