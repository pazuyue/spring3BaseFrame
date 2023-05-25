package com.example.springdemo.Config.RabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeAndQueueConfig {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    /**
     * 生成交换机，绑定队列
     *
     * @param exchange   交换机
     * @param routingKey 路由
     * @param queueName  队列
     */
    public void createDirectBindQueue(String exchange, String routingKey, String queueName) {
        //声明
        DirectExchange directExchange = new DirectExchange(exchange, true, false);
        Queue queue = new Queue(queueName, true, false, false);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);
        //创建
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareBinding(binding);
    }
}

