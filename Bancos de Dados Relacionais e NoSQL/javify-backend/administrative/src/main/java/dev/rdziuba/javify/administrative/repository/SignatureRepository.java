package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.Signature;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SignatureRepository extends JpaRepository<Signature, Integer> {
    @Query("SELECT s FROM Signature s " +
            "JOIN FETCH s.plan")
    List<Signature> findAllWithPlan();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Signature s WHERE s.id = :id")
    Optional<Signature> findByIdForUpdate(@Param("id") Integer id);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2"))
    @Query("""
            SELECT s
            FROM Signature s
            WHERE s.active = true
            ORDER BY s.id
            """)
    List<Signature> findBatchForProcessing(Pageable pageable);

    @Query(value = "SELECT calculate_refund(:signatureId)", nativeQuery = true)
    BigDecimal calculateRefund(@Param("signatureId") Integer signatureId);
}
