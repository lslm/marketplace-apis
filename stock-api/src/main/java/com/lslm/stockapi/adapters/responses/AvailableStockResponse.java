package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AvailableStockResponse(
        UUID productId,
        Integer availableQuantity
) {
}
