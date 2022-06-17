package com.lslm.stockapi.adapters.responses;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String description,
        BigDecimal price
) {
}
