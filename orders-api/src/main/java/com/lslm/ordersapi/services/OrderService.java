package com.lslm.ordersapi.services;

import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.entities.ProductStock;
import com.lslm.ordersapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order, ProductStock productStock) {
        if (order.getQuantity() <= productStock.getAvailableQuantity())
            return orderRepository.save(order);

        return null;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
