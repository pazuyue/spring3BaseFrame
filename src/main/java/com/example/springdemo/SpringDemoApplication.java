package com.example.springdemo;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.commonadvice","com.example.springdemo"})
//@ComponentScan(basePackages = {"com.example.commonadvice"}) //根据实际情况修改包路径
@MapperScan({"com.example.springdemo.Mapper"})
@EnableCaching //启动 Cache 功能
@EnableSpringUtil
public class SpringDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
