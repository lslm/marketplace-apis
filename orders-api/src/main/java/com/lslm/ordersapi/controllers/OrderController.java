package com.lslm.ordersapi.controllers;

import com.lslm.ordersapi.clients.StockClient;
import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.entities.ProductStock;
import com.lslm.ordersapi.producers.OrderProducer;
import com.lslm.ordersapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order newOrder) throws IOException {
        ProductStock productStock = stockClient.getProductStock(newOrder.getProductId());

        if (productStock == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product stock not found");

        Order order = orderService.create(newOrder, productStock);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock not available");

        orderProducer.produceOrderCreated(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
