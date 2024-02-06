package com.example.springdemo;

import com.google.common.util.concurrent.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuavaTest {

    @Test
    public void testDemoOne()
    {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something.....");
                // 模拟一些长时间的操作
                Thread.sleep(2000);
                return "Hello, Guava!";
            }
        });

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // 当异步任务成功完成时，这里会被调用
                System.out.println("异步处理成功，结果是：" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                // 异常处理
                t.printStackTrace();
            }
        }, service);



    }
}
