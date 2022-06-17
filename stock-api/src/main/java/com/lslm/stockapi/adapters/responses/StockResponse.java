package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record StockResponse(
        UUID id,
        UUID productId,
        Integer quantity,
        Instant createdAt
) {
}
