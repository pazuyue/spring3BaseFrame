package com.example.springdemo.Service.Impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TestServiceImpl {
    /**
     *
     * @param i
     */
    @Async
    public void executeAsync(Integer i) throws Exception{
        System.out.println("线程ID：" + Thread.currentThread().getId() + "线程名字：" +Thread.currentThread().getName()+"执行异步任务:" + i);
    }

    @Async
    public Future<String> executeAsyncPlus(Integer i) throws Exception {
        System.out.println("线程ID：" + Thread.currentThread().getId() +"线程名字：" +Thread.currentThread().getName()+ "执行异步有返回的任务:" + i);
        return new AsyncResult<>("success:"+i);
    }
}
