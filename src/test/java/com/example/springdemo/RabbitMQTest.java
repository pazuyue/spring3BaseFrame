package com.example.springdemo;


import com.example.springdemo.Config.RabbitMq.MQProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringDemoApplication.class })
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MQProperties mqProperties;



    @Test
    public void testSendMessage() {
        for (int i = 0; i <= 10; i++) {
            rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(),
                    "yueguangRouteKey", "发送了一条信息");
        }
    }



}

