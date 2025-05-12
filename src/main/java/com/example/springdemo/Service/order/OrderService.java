package com.example.springdemo.Service.order;

import com.example.springdemo.Entity.OrderInfo.Order;

import java.util.Map;

public interface OrderService {
    Order create();
    Order pay(Integer id);
    Order deliver(Integer id);
    Order receive(Integer id);
    Order cancel(Integer id);
    Map<Integer, Order> getOrders();

}
