package dev.rdziuba.javify.administrative.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @NotBlank @Size(max = 8) String postalCode,
        @Size(max = 150) String street,
        @Size(max = 100) String neighborhood
) {
}
