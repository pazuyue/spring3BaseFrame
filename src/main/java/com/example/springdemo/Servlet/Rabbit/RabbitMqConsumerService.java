package com.example.springdemo.Servlet.Rabbit;

import com.example.springdemo.Utils.RabbitMQ.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

public class RabbitMqConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumerService.class);

     @RabbitListener(queues = "${mq.queue}")
    public void receive(String payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        LOGGER.info("消费内容为：{}", payload);
        RabbitMQUtils.askMessage(channel, tag, LOGGER);
    }
}
