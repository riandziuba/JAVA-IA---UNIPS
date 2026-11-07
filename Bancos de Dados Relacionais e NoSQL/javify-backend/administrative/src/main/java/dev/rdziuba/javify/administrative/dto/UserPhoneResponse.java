package dev.rdziuba.javify.administrative.dto;

public record UserPhoneResponse(
        Integer id,
        Long userId,
        String number,
        String type
) {
}
