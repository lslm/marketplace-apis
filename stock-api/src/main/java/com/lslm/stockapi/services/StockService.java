package com.lslm.stockapi.services;

import com.lslm.stockapi.clients.ProductClient;
import com.lslm.stockapi.entities.AvailableStock;
import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return stockRepository.findById(id).orElse(null);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public AvailableStock availableStock(UUID productId) {
        List<Stock> stocks = stockRepository.findByProductId(productId);
        int availableQuantity = stocks.stream().mapToInt(Stock::getQuantity).sum();
        return new AvailableStock(productId, availableQuantity);
    }
}
