package dev.rdziuba.javify.administrative.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email @Size(max = 150) String email,
        @NotBlank @Size(max = 60) String passwordHash,
        @NotNull Boolean holder,
        @Past LocalDate birthday,
        @NotNull AddressRequest address,
        Integer planId,
        Integer signatureId
) {
}
