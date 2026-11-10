package dev.rdziuba.javify.administrative.dto;

import java.math.BigDecimal;

public record CancellationResponse(
        Integer signatureId,
        BigDecimal refund
) {
}
