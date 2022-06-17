package com.lslm.stockapi.controllers;

import com.lslm.stockapi.adapters.StockAdapter;
import com.lslm.stockapi.adapters.requests.CreateStockRequest;
import com.lslm.stockapi.adapters.responses.AvailableStockResponse;
import com.lslm.stockapi.adapters.responses.DetailedStockResponse;
import com.lslm.stockapi.adapters.responses.StockResponse;
import com.lslm.stockapi.clients.ProductClient;
import com.lslm.stockapi.entities.AvailableStock;
import com.lslm.stockapi.entities.Product;
import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockAdapter stockAdapter;

    @Autowired
    private ProductClient productClient;

    @PostMapping()
    public ResponseEntity<DetailedStockResponse> create(@RequestBody CreateStockRequest createStockRequest) throws IOException {
        Product product = productClient.getById(createStockRequest.productId());

        if (product == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product does not exists");

        Stock stock = stockService.create(stockAdapter.toStock(createStockRequest));

        if (stock != null)
            return new ResponseEntity<>(stockAdapter.toDetailedResponse(stock, product), HttpStatus.CREATED);

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock could not be created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedStockResponse> find(@PathVariable UUID id) {
        Stock stock = stockService.find(id);

        if (stock == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found");

        Product product = productClient.getById(stock.getProductId());
        return new ResponseEntity<>(stockAdapter.toDetailedResponse(stock, product), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<StockResponse>> findAll() {
        List<Stock> stocks = stockService.findAll();
        return new ResponseEntity<>(stockAdapter.toListResponse(stocks), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/available")
    public ResponseEntity<AvailableStockResponse> getByProduct(@PathVariable UUID productId) {
        AvailableStock availableStock = stockService.availableStock(productId);

        return new ResponseEntity<>(
                stockAdapter.toAvailableStockResponse(availableStock),
                HttpStatus.OK
        );
    }
}
