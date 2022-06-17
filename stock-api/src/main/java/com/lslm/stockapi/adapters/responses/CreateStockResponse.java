package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateStockResponse(
        UUID id,
        UUID productId,
        Integer quantity
) {
}
