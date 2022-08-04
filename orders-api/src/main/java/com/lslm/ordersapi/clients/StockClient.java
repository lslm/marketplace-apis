package com.lslm.ordersapi.clients;

import com.lslm.ordersapi.entities.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

@Component
public class StockClient {
    final String BASE_URL = "http://STOCK-API/api/stocks";

    @Autowired
    private RestTemplate restTemplate;

    public ProductStock getProductStock(UUID productId) throws IOException {
        ProductStock productStock = restTemplate.getForObject(
                BASE_URL + "/products/" + productId.toString() + "/available",
                ProductStock.class,
                productId
        );

        assert productStock != null;
        return productStock;
    }
}
