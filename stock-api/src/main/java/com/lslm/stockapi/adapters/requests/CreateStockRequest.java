package com.lslm.stockapi.adapters.requests;

import java.util.UUID;

public record CreateStockRequest(
        UUID productId,
        Integer quantity
) {
}
