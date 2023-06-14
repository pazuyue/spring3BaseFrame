package com.example.springdemo;

import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Service.Impl.PullOrders.JdpTbTradeServiceImpl;
import com.example.springdemo.Service.Impl.User.UserServiceImpl;
import com.example.springdemo.Tool.DynamicRoutingDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MybatisTest {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    DynamicRoutingDataSource dataSource;

    @Autowired
    JdpTbTradeServiceImpl jdpTbTradeService;

    @Test
    public void testSelectUser()
    {
        //DynamicDataSourceContextHolder.push("master");//手动切换
        System.out.println(userService.list());
    }

    @Test
    public void testSwitchDataSource()
    {
        System.out.println(userService.list());
        dataSource.switchDataSource("1");
        String tid = "9009999681807944198";
        JdpTbTrade jdpTbTrade = jdpTbTradeService.getOneByTid(tid,"tid","status");
        System.out.println("jdpTbTrade"+jdpTbTrade.toString());
        System.out.println(userService.list());

    }
}
