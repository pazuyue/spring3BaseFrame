package com.example.springdemo.Listener;

import com.example.springdemo.Config.Common.Enums.OrderStatus;
import com.example.springdemo.Config.Common.Enums.OrderStatusChangeEvent;
import com.example.springdemo.Entity.OrderInfo.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 订单状态监听器
 */
@Component("OrderStateListener")
@WithStateMachine(name = "OrderStateMachine")
public class OrderStateListener {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEvent> message) {
        Order Order = (Order) message.getHeaders().get("order");
        assert Order != null;
        Order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        System.out.println("支付，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }
    @OnTransition(source = "WAIT_PAYMENT", target = "CANCEL")
    public boolean cancelTransition(Message<OrderStatusChangeEvent> message) {
        Order Order = (Order) message.getHeaders().get("order");
        assert Order != null;
        Order.setStatus(OrderStatus.CANCEL.getKey());
        System.out.println("支付，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }
    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order Order = (Order) message.getHeaders().get("order");
        assert Order != null;
        Order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        System.out.println("发货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order Order = (Order) message.getHeaders().get("order");
        Order.setStatus(OrderStatus.FINISH.getKey());
        System.out.println("收货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }
}

