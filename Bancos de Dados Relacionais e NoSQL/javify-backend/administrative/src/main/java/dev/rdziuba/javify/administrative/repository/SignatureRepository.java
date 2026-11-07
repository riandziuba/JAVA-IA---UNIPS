package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SignatureRepository extends JpaRepository<Signature, Integer> {
    @Query("SELECT a FROM Signature a " +
            "JOIN FETCH a.plan")
    List<Signature> findAllWithPlan();
}
