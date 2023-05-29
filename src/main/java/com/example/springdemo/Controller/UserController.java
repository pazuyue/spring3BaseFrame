package com.example.springdemo.Controller;

import com.example.springdemo.Interface.ControllerWebLog;
import com.example.springdemo.Pojo.User;
import com.example.springdemo.Servlet.User.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getOne")
    @ControllerWebLog(name = "查询", intoDb = true)
    public User getOne(Integer id) {
        return userService.getOne(id);
    }

    @GetMapping("/list")
    @ControllerWebLog(name = "查询用户列表", intoDb = true)
    public String showUserList(int pageNum, int pageSize, Model model) {
        PageInfo<User> pageInfo = userService.getUserList(pageNum, pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
