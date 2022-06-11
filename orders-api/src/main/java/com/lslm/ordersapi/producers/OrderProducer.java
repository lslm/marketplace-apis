package com.lslm.ordersapi.producers;

import com.lslm.ordersapi.adapters.OrderAdapter;
import com.lslm.ordersapi.entities.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
    @Autowired
    private final AmqpTemplate queueSender;

    @Autowired
    private OrderAdapter orderAdapter;

    public OrderProducer(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    public void produceOrderCreated(Order order) {
        queueSender.convertAndSend("ORDER", "CREATED", orderAdapter.orderToJson(order));
    }
}
