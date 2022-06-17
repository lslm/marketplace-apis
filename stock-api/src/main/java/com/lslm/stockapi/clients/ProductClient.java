package com.lslm.stockapi.clients;

import com.lslm.stockapi.adapters.ProductAdapter;
import com.lslm.stockapi.adapters.responses.ProductResponse;
import com.lslm.stockapi.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ProductClient {
    final String BASE_URL = "http://localhost:8081/api/products";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductAdapter productAdapter;

    public Product getById(UUID productId) {
        ProductResponse productResponse = restTemplate.getForObject(
                "http://PRODUCTS-API/api/products/{productId}",
                ProductResponse.class,
                productId
        );

        assert productResponse != null;
        return productAdapter.toProduct(productResponse);
    }
}
