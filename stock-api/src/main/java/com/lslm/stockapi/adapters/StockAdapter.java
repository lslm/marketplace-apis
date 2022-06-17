package com.lslm.stockapi.adapters;

import com.lslm.stockapi.adapters.requests.CreateStockRequest;
import com.lslm.stockapi.adapters.responses.CreateStockResponse;
import com.lslm.stockapi.entities.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockAdapter {
    public Stock toStock(CreateStockRequest createStockRequest) {
        return Stock.builder()
                .productId(createStockRequest.productId())
                .quantity(createStockRequest.quantity())
                .build();
    }

    public CreateStockResponse toResponse(Stock stock) {
        return CreateStockResponse.builder()
                .id(stock.getId())
                .productId(stock.getProductId())
                .quantity(stock.getQuantity())
                .build();
    }
}
