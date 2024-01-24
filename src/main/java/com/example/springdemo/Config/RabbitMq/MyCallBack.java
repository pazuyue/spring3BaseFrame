package com.example.springdemo.Config.RabbitMq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    // 设置 rabbitTemplate 的回调对象
    @PostConstruct
    public void init(){
        System.out.println("===========MyCallBack============");
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("hello world");
        String id = correlationData != null?correlationData.getId():"";
        if(ack){
            System.out.println("交换机已经收到id为："+id+"的消息");
            log.info("交换机已经收到id为：{}的消息",id);
        }else {
            System.out.println("交换机还未收到 id为："+id+"的消息,由于原因:"+cause);
            log.info("交换机还未收到 id为：{}的消息，由于原因：{}",id,cause);
        }

    }
}
