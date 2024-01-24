package com.example.springdemo.Service.Impl.Queues;

import com.example.springdemo.Service.Queues.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class YueguangQueueServiceImpl implements QueueService {
    @Override
    public String handle(String payload) {
        String name = Thread.currentThread().getName();
        log.info("name = " + name);
        log.info("消息：【" + payload + "】已出来！");
        return payload;
    }
}
