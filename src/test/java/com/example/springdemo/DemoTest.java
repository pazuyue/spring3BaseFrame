package com.example.springdemo;

import com.example.commonadvice.config.SnowflakeIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DemoTest {

    @Test
    public void testSnowflakeIdGenerator() {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1L,1L);
        long nextId = snowflakeIdGenerator.nextId();
        System.out.println(nextId);
    }
}
