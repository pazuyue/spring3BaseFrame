package com.example.springdemo.Service.Impl.order;

import com.example.springdemo.Config.Common.Enums.OrderStatus;
import com.example.springdemo.Config.Common.Enums.OrderStatusChangeEvent;
import com.example.springdemo.Entity.OrderInfo.Order;
import com.example.springdemo.Service.order.OrderService;
import jakarta.annotation.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;

    @Resource
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister;
    private int id = 1;
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order create() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMENT.getKey());
        order.setId(id++);
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Order pay(Integer id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试支付，订单号：" + id);
        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.PAYED).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 支付失败, 状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order cancel(Integer id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试取消，订单号：" + id);
        Message message = MessageBuilder.withPayload(OrderStatusChangeEvent.CANCEL).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 取消失败, 状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order deliver(Integer id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试发货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.DELIVERY).setHeader("order", order).build(), orders.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 发货失败，状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order receive(Integer id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试收货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.RECEIVED).setHeader("order", order).build(), orders.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 收货失败，状态异常，订单号：" + id);
        }
//可以获取返回值
        orderStateMachine.getExtendedState().get("order", Order.class);
        return orders.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }

    /**
     * 发送订单状态转换事件 这里不要使用synchronized锁方法，效率比较低，分布式系统优先采用分布式锁，下单锁userId，订单状态流转锁orderId根据业务考虑使用什么。（可以封装工具类）
     *
     * @param message
     * @param order
     * @return
     */
    private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            //尝试恢复状态机状态
            persister.restore(orderStateMachine, order);
            //添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            //持久化状态机状态
            persister.persist(orderStateMachine, order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}

