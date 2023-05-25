package com.example.springdemo.Servlet.Rabbit;

import com.example.springdemo.Config.RabbitMq.ExchangeAndQueueConfig;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import com.example.springdemo.Utils.RabbitMQ.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);


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

    @RabbitListener(queues = "${mq.queue}")
    public void receive(String payload, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        LOGGER.info("消费内容为：{}", payload);
        RabbitMQUtils.askMessage(channel, tag, LOGGER);
    }
}

