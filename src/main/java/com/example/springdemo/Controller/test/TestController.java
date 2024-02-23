package com.example.springdemo.Controller.test;

import com.example.commonadvice.config.SnowflakeIdGenerator;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import com.example.springdemo.Service.Message.MessageService;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private MQProperties mqProperties;
    @Resource
    private MessageService messageService;

    @GetMapping(value = "/testSendMessage")
    public ResponseEntity<Object> testSendMessage(){
        for (int i = 0; i <= 10; i++) {
            System.out.println("发送了一条信息:"+i);
           /* rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(),
                    "yueguangRouteKey", "发送了一条信息");*/
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setExpiration("15000"); // 过期的毫秒数
            Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();

            CorrelationData correlationData = new CorrelationData(); // 关联数据
            correlationData.setId("order_123456");
            messageService.sendMsg(mqProperties.getDefaultExchange(), "yueguangRouteKey", message,correlationData);
            //rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(), "yueguangRouteKey", message);
        }
        return new ResponseEntity<>("successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/testSnowflakeIdGenerator")
    public  ResponseEntity<Object> testSnowflakeIdGenerator(){
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1L,1L);
        long nextId = snowflakeIdGenerator.nextId();
        System.out.println(nextId);
        return new ResponseEntity<>("successfully:"+nextId, HttpStatus.OK);
    }
}