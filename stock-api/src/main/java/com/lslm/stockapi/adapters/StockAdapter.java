package com.lslm.stockapi.adapters;

import com.lslm.stockapi.adapters.requests.CreateStockRequest;
import com.lslm.stockapi.adapters.responses.AvailableStockResponse;
import com.lslm.stockapi.adapters.responses.DetailedStockResponse;
import com.lslm.stockapi.adapters.responses.StockResponse;
import com.lslm.stockapi.entities.AvailableStock;
import com.lslm.stockapi.entities.Product;
import com.lslm.stockapi.entities.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockAdapter {
    @Autowired
    private ProductAdapter productAdapter;

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

    public DetailedStockResponse toDetailedResponse(Stock stock, Product product) {
        return DetailedStockResponse
                .builder()
                .id(stock.getId())
                .product(productAdapter.toProductResponse(product))
                .quantity(stock.getQuantity())
                .build();
    }

    public List<StockResponse> toListResponse(List<Stock> stocks) {
        return stocks
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AvailableStockResponse toAvailableStockResponse(AvailableStock availableStock) {
        return AvailableStockResponse
                .builder()
                .productId(availableStock.productId())
                .availableQuantity(availableStock.availableQuantity())
                .build();
    }
}
