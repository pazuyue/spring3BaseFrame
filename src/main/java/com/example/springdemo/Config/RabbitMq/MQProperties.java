package com.example.springdemo.Config.RabbitMq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MQProperties {
    private String defaultExchange; //一般交换机
    private Map<String,String> queueAndRouteKey;
    private String deadExchange; //死信交换机
    private String deadQueue; //死信队列
    private String deadRoutingKey;
    private String deadRoutingKeyLabel; //死信路由键的标志
    private String deadExchangeLabel; //死信交换器的标志

}

