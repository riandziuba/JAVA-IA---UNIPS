package dev.rdziuba.javify.administrative.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreditCardRequest(
        @NotNull Integer signatureId,
        @Size(max = 100) String cardholderName,
        @Size(max = 4) String lastFourDigits,
        String gatewayToken,
        LocalDate expirationDate
) {
}
