package com.example.springdemo;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.example.springdemo.Service.Impl.User.UserServiceImpl;
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


    @Test
    public void testSelectUser()
    {
        DynamicDataSourceContextHolder.push("master");//手动切换
        System.out.println(userService.list());

    }
}
