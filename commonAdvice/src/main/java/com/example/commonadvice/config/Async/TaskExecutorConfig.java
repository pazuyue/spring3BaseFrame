package com.example.commonadvice.config.Async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync  //开启异步操作
public class TaskExecutorConfig implements AsyncConfigurer {

    @Value("${spring.application.name}")
    private String springName;
    /**
     * 通过getAsyncExecutor方法配置ThreadPoolTaskExecutor,获得一个基于线程池TaskExecutor
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("CustomThread-");
        pool.setCorePoolSize(5);//核心线程数
        pool.setMaxPoolSize(10);//最大线程数
        pool.setQueueCapacity(25);//线程队列
        pool.initialize();//线程初始化
        return pool;
    }
}
