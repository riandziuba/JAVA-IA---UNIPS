package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Query("SELECT p.price FROM Plan p Where p.id = :id")
    BigDecimal searchPriceForId(@Param("id") Integer id);
}
