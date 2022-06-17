package com.lslm.stockapi.adapters;

import com.lslm.stockapi.adapters.requests.CreateStockRequest;
import com.lslm.stockapi.adapters.responses.StockResponse;
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

    public StockResponse toResponse(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .productId(stock.getProductId())
                .quantity(stock.getQuantity())
                .build();
    }
}
