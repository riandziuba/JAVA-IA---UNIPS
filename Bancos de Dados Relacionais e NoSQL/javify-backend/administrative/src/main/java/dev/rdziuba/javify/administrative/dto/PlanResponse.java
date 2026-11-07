package dev.rdziuba.javify.administrative.dto;

import java.math.BigDecimal;

public record PlanResponse(
        Integer id,
        String name,
        BigDecimal price,
        boolean hasAdvertising,
        int membersLimit,
        boolean offlineMode
) {
}
