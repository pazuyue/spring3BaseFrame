package com.example.springdemo.Config.RabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Autowired
    private MQProperties mqProperties;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean
    public Map<String,Queue> queues() {
        Map<String,Queue> queues = new HashMap<>();
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        Iterator<Map.Entry<String,String>> iterator=mqProperties.getQueueAndRouteKey().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            queues.put(entry.getValue(),new Queue(entry.getValue(), durable,exclusive,autoDelete));
        }
        return queues;
    }

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(mqProperties.getDefaultExchange(), durable, autoDelete);
    }

    @Bean
    public List<Binding> bindings(DirectExchange directExchange, Map<String,Queue> queues,RabbitAdmin rabbitAdmin) {
        List<Binding> bindings = new ArrayList<>();
        Iterator<Map.Entry<String,String>> iterator=mqProperties.getQueueAndRouteKey().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            String queueName = entry.getValue();
            String routingKey = entry.getKey();
            Queue queue =queues.get(queueName);
            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);
            bindings.add(binding);
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareBinding(binding);
        }
        return bindings;
    }




    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}

