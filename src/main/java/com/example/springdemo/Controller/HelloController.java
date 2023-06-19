package com.example.springdemo.Controller;

import com.example.springdemo.api.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Result hello() {
        return Result.success("请求成功，返回数据");
    }
}
