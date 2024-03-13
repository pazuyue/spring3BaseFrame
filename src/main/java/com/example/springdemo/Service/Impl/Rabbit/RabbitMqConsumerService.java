package com.example.springdemo.Service.Impl.Rabbit;

import cn.hutool.extra.spring.SpringUtil;
import com.example.springdemo.Service.Queues.QueueService;
import com.example.springdemo.Utils.RabbitMQ.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumerService.class);

    //@RabbitListener(queues = "${mq.queues}")
    @RabbitListener(queues = "#{'${mq.queues}'.split(',')}")
    public void receive(String payload, Channel channel, MessageProperties properties, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException {
        LOGGER.info("消费properties：{}", properties);
        LOGGER.info("消费channel：{}", channel);
        String queue = properties.getConsumerQueue();
        String queueBean = queue+"ServiceImpl";
        QueueService queueService = SpringUtil.getBean(queueBean);
        queueService.handle(payload);
        Thread.sleep(1000); // 休眠一秒，好看效果
        RabbitMQUtils.askMessage(channel, tag, LOGGER);
    }
}
