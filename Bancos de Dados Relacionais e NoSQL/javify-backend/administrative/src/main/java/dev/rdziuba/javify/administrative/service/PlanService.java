package dev.rdziuba.javify.administrative.service;

import dev.rdziuba.javify.administrative.domain.Plan;
import dev.rdziuba.javify.administrative.dto.PlanRequest;
import dev.rdziuba.javify.administrative.dto.PlanResponse;
import dev.rdziuba.javify.administrative.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<PlanResponse> findAll() {
        return planRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public PlanResponse findById(Integer id) {
        return planRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + id));
    }

    @Transactional
    public PlanResponse create(PlanRequest request) {
        var plan = new Plan();
        plan.setName(request.name());
        plan.setPrice(request.price());
        plan.setHasAdvertising(request.hasAdvertising());
        plan.setMembersLimit(request.membersLimit());
        plan.setOfflineMode(request.offlineMode());
        return toResponse(planRepository.save(plan));
    }

    @Transactional
    public PlanResponse update(Integer id, PlanRequest request) {
        var plan = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found: " + id));
        plan.setName(request.name());
        plan.setPrice(request.price());
        plan.setHasAdvertising(request.hasAdvertising());
        plan.setMembersLimit(request.membersLimit());
        plan.setOfflineMode(request.offlineMode());
        return toResponse(planRepository.save(plan));
    }

    @Transactional
    public void delete(Integer id) {
        if (!planRepository.existsById(id)) {
            throw new EntityNotFoundException("Plan not found: " + id);
        }
        planRepository.deleteById(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void demonstrateRepeatableRead(Integer planId) throws InterruptedException {
        var firstPrice = planRepository.searchPriceForId(planId);

        System.out.println("First read: " + firstPrice);
        System.out.println("Update the price on Dbeaver e execute the commit...");

        Thread.sleep(15_000);

        var secondPrice = planRepository.searchPriceForId(planId);

        System.out.println("Second read: " + secondPrice);
    }

    private PlanResponse toResponse(Plan plan) {
        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getPrice(),
                plan.isHasAdvertising(),
                plan.getMembersLimit(),
                plan.isOfflineMode()
        );
    }
}
