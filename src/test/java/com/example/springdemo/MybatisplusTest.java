package com.example.springdemo;

import com.example.springdemo.Config.Mybaits.DynamicDataSource;
import com.example.springdemo.Mapper.UserMapper;
import com.example.springdemo.entity.User;
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
        DynamicDataSource.name.set("w");
        //其中参数是wrapper，条件构造器，这里不用的话可以写null，表示查询所有的用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        DynamicDataSource.name.set("s");
        User user = new User();
        user.setName("月光");
        user.setAge(21);
        user.setEmail("2425540101@qq.com");

        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }


}
