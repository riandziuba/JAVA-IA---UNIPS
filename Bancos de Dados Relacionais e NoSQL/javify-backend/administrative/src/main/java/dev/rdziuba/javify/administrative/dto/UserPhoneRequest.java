package dev.rdziuba.javify.administrative.dto;

import dev.rdziuba.javify.administrative.domain.PhoneType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserPhoneRequest(
        @NotNull Long userId,
        @NotBlank String number,
        @NotNull PhoneType type
) {
}
