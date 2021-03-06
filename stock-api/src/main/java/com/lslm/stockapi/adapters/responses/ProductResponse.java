package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID id,
        String description,
        BigDecimal price
) {
}
