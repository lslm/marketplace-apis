package com.lslm.stockapi.entities;

import java.util.UUID;

public record AvailableStock(UUID productId, int availableQuantity) {}
