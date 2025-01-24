package com.example.springdemo.Service.Message;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String exchange, String routingKey,Message message) {
        CorrelationData correlationData = new CorrelationData(); // 关联数据
        String messageID = IdUtil.simpleUUID();
        log.info("messageID="+messageID);
        correlationData.setId(messageID);
        rabbitTemplate.convertAndSend(exchange, routingKey, message,correlationData);
    }
}
