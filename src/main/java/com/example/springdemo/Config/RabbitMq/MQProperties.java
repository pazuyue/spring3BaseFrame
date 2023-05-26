package com.example.springdemo.Config.RabbitMq;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MQProperties {
    private String defaultExchange;
    private Map<String,String> queueAndRouteKey;
}

