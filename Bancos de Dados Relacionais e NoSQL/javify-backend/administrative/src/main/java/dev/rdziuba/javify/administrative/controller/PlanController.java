package dev.rdziuba.javify.administrative.controller;

import dev.rdziuba.javify.administrative.dto.PlanRequest;
import dev.rdziuba.javify.administrative.dto.PlanResponse;
import dev.rdziuba.javify.administrative.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> list() {
        return ResponseEntity.ok(planService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(planService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlanResponse> create(@RequestBody @Valid PlanRequest request) {
        var response = planService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> update(@PathVariable Integer id,
                                                   @RequestBody @Valid PlanRequest request) {
        return ResponseEntity.ok(planService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        planService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/isolation")
    public ResponseEntity<Void> testIsolation() throws InterruptedException {
        planService.demonstrateRepeatableRead(2);
        return ResponseEntity.noContent().build();
    }
}
