package com.lslm.stockapi.adapters;

import com.lslm.stockapi.adapters.responses.ProductResponse;
import com.lslm.stockapi.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAdapter {
    public Product toProduct(ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.id())
                .description(productResponse.description())
                .price(productResponse.price())
                .build();
    }
}
