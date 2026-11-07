package dev.rdziuba.javify.administrative.dto;

public record SignatureResponse(
        Integer id,
        PlanResponse plan,
        boolean active
) {
}
