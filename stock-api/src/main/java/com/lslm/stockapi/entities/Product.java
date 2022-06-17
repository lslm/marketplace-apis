package com.lslm.stockapi.entities;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public class Product {
    private UUID id;
    private String description;
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
