package com.lslm.ordersapi.adapters;

import com.google.gson.Gson;
import com.lslm.ordersapi.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {
    private Gson gson = new Gson();

    public String orderToJson(Order order) {
        return gson.toJson(order);
    }
}
