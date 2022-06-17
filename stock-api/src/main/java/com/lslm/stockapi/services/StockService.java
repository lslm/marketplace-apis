package com.lslm.stockapi.services;

import com.lslm.stockapi.adapters.responses.ProductResponse;
import com.lslm.stockapi.clients.ProductClient;
import com.lslm.stockapi.entities.Product;
import com.lslm.stockapi.entities.ProductStock;
import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductClient productClient;

    private boolean doesProductExist(Stock stock) throws IOException {
        return productClient.getById(stock.getProductId()) != null;
    }

    public Stock create(Stock stock) throws IOException {
        if (doesProductExist(stock))
            return stockRepository.save(stock);

        return null;
    }

    public Stock find(UUID id) {
        return stockRepository.findById(id).orElseThrow();
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public ProductStock byProduct(UUID productId) {
        List<Stock> stocks = stockRepository.findAll();

        List<Stock> productStock = stocks.stream().filter(stock -> stock.getProductId().equals(productId)).toList();
        List<Integer> quantities = productStock.stream().map(Stock::getQuantity).toList();

        int availableQuantity = quantities.stream().reduce(0, Integer::sum);

        return new ProductStock(productId, availableQuantity);
    }
}
