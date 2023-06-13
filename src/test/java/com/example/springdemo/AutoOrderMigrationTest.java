package com.example.springdemo;

import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Entity.User.User;
import com.example.springdemo.Service.Impl.PullOrders.JdpTbTradeServiceImpl;
import com.example.springdemo.Service.Impl.PullOrders.TmpullOrders;
import com.example.springdemo.Service.Impl.TChannelOrderLog.TChannelOrderLogServiceImpl;
import com.example.springdemo.Service.Impl.User.UserServiceImpl;
import com.example.springdemo.Service.Impl.order.OrderTransferServiceImpl;
import com.example.springdemo.Tool.CustomIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AutoOrderMigrationTest {

    @Autowired
    private OrderTransferServiceImpl orderTransferService;
    @Autowired
    private JdpTbTradeServiceImpl jdpTbTradeService;
    @Autowired
    private TChannelOrderLogServiceImpl tChannelOrderLogService;
    @Autowired
    private TmpullOrders tmpullOrders;
    @Autowired
    private CustomIdGenerator customIdGenerator;
    @Autowired
    private UserServiceImpl userService;

    ExecutorService executorService = new ThreadPoolExecutor(
            10, // 核心线程数
            20, // 最大线程数
            60, // 线程空闲时间
            TimeUnit.SECONDS, // 时间单位
            new LinkedBlockingQueue<>() // 等待队列
    );

    @Test
    public void autoOrderMigration()
    {
        orderTransferService.autoOrderMigration(1);
    }

    @Test
    public void testTransactional()
    {
        User user = new User();
        user.setAge(1);
        user.setEmail("yueguang@qq.com");
        user.setName("yueguang");
        userService.insert(user);
    }

    @Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023,Calendar.MAY,30,18,00);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        System.out.println(dateString);
    }

    @Test
    public void testPullOrder(){
        String startTime="2023-05-30 00:00:00";
        String endTime = "2023-05-31 00:00:00";
        try {
            tmpullOrders.pullOrder(startTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate()
    {
       TChannelOrderLog tChannelOrderLog =tChannelOrderLogService.getById(2);
       tChannelOrderLog.setChannelId(9);
       tChannelOrderLogService.updateById(tChannelOrderLog);
    }

    @Test
    public void testGetOneByTid()
    {
        String tid = "9009999681807944198";
        JdpTbTrade jdpTbTrade = jdpTbTradeService.getOneByTid(tid,"tid","status");
        System.out.println("jdpTbTrade"+jdpTbTrade.toString());
    }

    @Test
    public void testGetID()
    {
        String key = "XS" + "_MAXID";
        String id = customIdGenerator.generateOrderId(key);
        System.out.println(id);
    }

    @Test
    public void doSomething() {
        // 创建一个 CountDownLatch 对象
        CountDownLatch latch = new CountDownLatch(10);
        // 启动 10 个任务
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                // 执行某个函数
                System.out.println("当前线程：" + Thread.currentThread().getName());
                String key = "XS" + "_MAXID";
                for (int j = 0; j < 10 ; j++) {
                    //long id = customIdGenerator.getID(key);
                    String id = customIdGenerator.generateOrderId(key);
                    System.out.println(id);
                }

                // 执行完毕后调用 countDown() 方法
                latch.countDown();
            });
        }
        try {
            // 等待所有任务执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetID2()
    {
        int t= 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                String key = "XS" + "_MAXID";
                long id = customIdGenerator.getID(key);
                System.out.println(id);
                //System.out.println(t++);
            }
        }
    }
}
