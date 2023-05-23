package com.example.springdemo;

import com.example.springdemo.Mapper.UserMapper;
import com.example.springdemo.Pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringDemoApplication.class })
public class MybatisplusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        //其中参数是wrapper，条件构造器，这里不用的话可以写null，表示查询所有的用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
