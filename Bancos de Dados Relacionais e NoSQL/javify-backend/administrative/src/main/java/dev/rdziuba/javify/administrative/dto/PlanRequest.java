package dev.rdziuba.javify.administrative.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlanRequest(
        @NotBlank @Size(max = 50) String name,
        @NotNull @Positive BigDecimal price,
        @NotNull Boolean hasAdvertising,
        @NotNull @Positive Integer membersLimit,
        @NotNull Boolean offlineMode
) {
}
