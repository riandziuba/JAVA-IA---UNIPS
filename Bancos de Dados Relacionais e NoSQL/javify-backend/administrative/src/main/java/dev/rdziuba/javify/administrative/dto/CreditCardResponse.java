package dev.rdziuba.javify.administrative.dto;

import java.time.LocalDate;

public record CreditCardResponse(
        Integer id,
        Integer signatureId,
        String cardholderName,
        String lastFourDigits,
        String gatewayToken,
        LocalDate expirationDate
) {
}
