package com.lslm.ordersapi.controllers;

import com.lslm.ordersapi.clients.StockClient;
import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.entities.ProductStock;
import com.lslm.ordersapi.producers.OrderProducer;
import com.lslm.ordersapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

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

        Order order = orderService.create(newOrder, productStock);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock not available");

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
