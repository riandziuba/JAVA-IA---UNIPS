package dev.rdziuba.javify.administrative.dto;

import java.time.LocalDate;
import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        boolean holder,
        LocalDate birthday,
        AddressResponse address,
        SignatureResponse signature,
        List<UserPhoneResponse> phones
) {
}
