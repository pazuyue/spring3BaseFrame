package com.example.springdemo;

import com.example.springdemo.Config.RabbitMq.MQProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PropertiesTest {

    @Autowired
    private MQProperties mqProperties;

    @Test
    public void test() {
        System.out.println("getDefaultExchange = " + mqProperties.getDefaultExchange());
        //System.out.println("getQueueAndRouteKey = " + mqProperties.getQueueAndRouteKey());
    }
}
