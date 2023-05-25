package com.example.springdemo;


import com.example.springdemo.Config.RabbitMq.ExchangeAndQueueConfig;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import com.example.springdemo.Servlet.Rabbit.RabbitMQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringDemoApplication.class })
public class RabbitMQTest {

    //@Autowired
    //RabbitMQService rabbitMQService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQProperties mqProperties;

    @Test
    public void testSendMessage() {
        rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(),
                mqProperties.getRouteKey(), "发送了一条信息");

        //rabbitMQService.convertAndSend("TestQueue","发送了一条信息-测试多队列");

    }
}

