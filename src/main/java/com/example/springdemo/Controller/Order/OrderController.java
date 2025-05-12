package com.example.springdemo.Controller.Order;

import com.example.springdemo.Entity.OrderInfo.Order;
import com.example.springdemo.Service.order.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;
    @RequestMapping("/create")
    public Order create(){
        return orderService.create();
    }

    @RequestMapping("/cancel")
    public Order cancel(Integer orderId){
        return orderService.cancel(orderId);
    }
    @RequestMapping("/pay")
    public Order pay(Integer orderId){
        return orderService.pay(orderId);
    }
    @RequestMapping("/deliver")
    public Order deliver(Integer orderId){
        return orderService.deliver(orderId);
    }
    @RequestMapping("/receive")
    public Order receive(Integer orderId){
        return orderService.receive(orderId);
    }
}
