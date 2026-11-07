package dev.rdziuba.javify.administrative.repository;

import dev.rdziuba.javify.administrative.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
