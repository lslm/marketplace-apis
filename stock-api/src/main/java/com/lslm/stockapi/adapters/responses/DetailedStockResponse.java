package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record DetailedStockResponse(
        UUID id,
        ProductResponse product,
        Integer quantity,
        Instant createdAt
) {
}
