package com.example.springdemo.Servlet.Rabbit;

import com.example.springdemo.Config.RabbitMq.ExchangeAndQueueConfig;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQProperties mqProperties;
    @Autowired
    private ExchangeAndQueueConfig exchangeAndQueueConfig;


    public void convertAndSend(String queueName, Object object)
    {
        exchangeAndQueueConfig.createDirectBindQueue(
                mqProperties.getDefaultExchange(),
                mqProperties.getRouteKey(), queueName);

        //发消息
        rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange()
                ,mqProperties.getRouteKey(),
                object);
    }
}
