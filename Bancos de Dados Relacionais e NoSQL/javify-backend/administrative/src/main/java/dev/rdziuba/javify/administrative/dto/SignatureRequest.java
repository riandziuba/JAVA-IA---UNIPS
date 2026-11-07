package dev.rdziuba.javify.administrative.dto;

import jakarta.validation.constraints.NotNull;

public record SignatureRequest(
        @NotNull Integer planId,
        @NotNull Boolean active
) {
}
