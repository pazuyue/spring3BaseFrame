package com.example.springdemo.Config.RabbitMq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    // 设置 rabbitTemplate 的回调对象
    @PostConstruct
    public void init(){
        System.out.println("===========MyCallBack============");
        rabbitTemplate.setConfirmCallback(this);
        //注入接口实现
        rabbitTemplate.setReturnsCallback(this);
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

    /**
     * 回退接口实现的方法
     * 消息传递过程中不可达目的地消息返回给生产者
     * 只有不达目的才会进行回退
     * @param returnedMessage
     */
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("回退消息{}",returnedMessage);
    }
}
