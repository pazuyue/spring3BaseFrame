package com.example.springdemo.Controller.RabbitMQ;

import cn.hutool.core.lang.copier.Copier;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test_queue")
public class TestQueueController {
    @Autowired
    private MQProperties mqProperties;
    @Autowired
    private MessageService messageService;


    @GetMapping(value = "/testSendMsg")
    public ResponseEntity<Object> testSendMsg(@RequestParam String msg) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("15000"); // 过期的毫秒数
        Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(messageProperties).build();

        CorrelationData correlationData = new CorrelationData(); // 关联数据
        String messageID = IdUtil.simpleUUID();
        log.info("messageID="+messageID);
        correlationData.setId(messageID);
        messageService.sendMsg(mqProperties.getDefaultExchange(), "yueguangRouteKey", message,correlationData);
        log.info("消息：【" + msg + "】已发送！");
        return new ResponseEntity<>("updated successfully"+msg, HttpStatus.OK);
    }
}
