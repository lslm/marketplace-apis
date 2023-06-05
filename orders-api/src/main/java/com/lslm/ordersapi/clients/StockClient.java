package com.lslm.ordersapi.clients;

import com.lslm.ordersapi.entities.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Component
public class StockClient {
    @Autowired
    private RestTemplate restTemplate;

    public ProductStock getProductStock(UUID productId) throws IOException {
        String url = "http://localhost:8081/api/stocks/products/" + productId.toString() + "/available";

        try {
            ResponseEntity<ProductStock> responseEntity = new RestTemplate().getForEntity(url, ProductStock.class);
            return responseEntity.getBody();
        } catch(HttpStatusCodeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }
}
