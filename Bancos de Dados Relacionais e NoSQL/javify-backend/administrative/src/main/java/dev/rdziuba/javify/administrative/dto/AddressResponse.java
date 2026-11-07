package dev.rdziuba.javify.administrative.dto;

public record AddressResponse(
        String postalCode,
        String street,
        String neighborhood
) {
}
