package com.lslm.stockapi.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = {"${order.created}"})
    public void orderCreated(@Payload String body) {
        System.out.println("Message received: " + body);
    }

}
