package com.example.springdemo.Listener;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {
    // 直接注入
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("项目关闭处理");
        // 关闭线程池
        executor.destroy();
    }
}
