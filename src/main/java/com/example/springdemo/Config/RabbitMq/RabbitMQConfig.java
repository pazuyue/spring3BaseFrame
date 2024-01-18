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
            Map<String, Object> args = new HashMap<>();
            // x-dead-letter-exchange：这里声明当前业务队列绑定的死信交换机
            args.put(mqProperties.getDeadExchangeLabel(), mqProperties.getDeadExchange());
            // x-dead-letter-routing-key：这里声明当前业务队列的死信路由 key
            args.put(mqProperties.getDeadRoutingKeyLabel(), mqProperties.getDeadRoutingKey());
            queues.put(entry.getValue(),new Queue(entry.getValue(), durable,exclusive,autoDelete,args));
        }
        return queues;
    }

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(mqProperties.getDefaultExchange(), durable, autoDelete);
    }

    /**
     * 定义业务队列使用的死信队列
     */
    @Bean
    public Map<String,Queue> deadLetterQueues() {
        Map<String,Queue> queues = new HashMap<>();
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        queues.put(mqProperties.getDeadQueue(),new Queue(mqProperties.getDeadQueue(), durable,exclusive,autoDelete));
        return queues;
    }

    /**
     * 定义死信队列使用的交换机
     */
    @Bean
    public TopicExchange deadLetterTopicExchange() {
        return new TopicExchange(mqProperties.getDeadExchange());
    }




    @Bean
    public List<Binding> bindings(TopicExchange deadLetterTopicExchange,DirectExchange directExchange, Map<String,Queue> queues,Map<String,Queue> deadLetterQueues,RabbitAdmin rabbitAdmin) {
        // 申明绑定死信交换机和对列
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

        Queue queue = deadLetterQueues.get(mqProperties.getDeadQueue());
        Binding binding = BindingBuilder.bind(queue).to(deadLetterTopicExchange).with(mqProperties.getDeadRoutingKey());
        bindings.add(binding);
        // 申明绑定死信交换机和对列
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
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

