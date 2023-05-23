package com.example.springdemo.Controller;

import com.example.springdemo.Interface.ControllerWebLog;
import com.example.springdemo.Pojo.User;
import com.example.springdemo.Servlet.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getOne")
    @ControllerWebLog(name = "查询", intoDb = true)
    public User getOne(Long id) {
        return userService.getOne(id);
    }

    /**
     *  查找所有
     * @return
     */
    @RequestMapping("/getAll")
    @ControllerWebLog(name = "查询", intoDb = true)
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/testLevel")
    @ControllerWebLog(name = "查询", intoDb = true)
    public void testLevel(){
        logger.trace(" --- trace --- ");
        logger.debug(" --- debug --- ");
        logger.info(" --- info --- ");
        logger.warn(" --- warn --- ");
        logger.error(" --- error --- ");
    }
}
