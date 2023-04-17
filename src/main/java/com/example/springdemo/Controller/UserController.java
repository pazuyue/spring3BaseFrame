package com.example.springdemo.Controller;

import com.example.springdemo.Interface.ControllerWebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/getOne")
    @ControllerWebLog(name = "查询", intoDb = true)
    public String getOne(Long id, String name) {
        return "1234";
    }
}