package com.example.springdemo.Service.Message;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String exchange, String routingKey,Message message,CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message,correlationData);
    }
}