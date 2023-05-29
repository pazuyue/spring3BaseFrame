package com.example.springdemo.Controller.Mail;

import com.example.springdemo.Interface.ControllerWebLog;
import com.example.springdemo.Tool.MailTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailTool mailTool;

    @RequestMapping("/send")
    @ControllerWebLog(name = "查询", intoDb = true)
    public String send() {
        mailTool.send();
        return "send success";
    }

    @RequestMapping("/sendHtml")
    @ControllerWebLog(name = "查询", intoDb = true)
    public String sendHtml() {
        mailTool.sendHtml();
        return "sendHtml success";
    }

    @RequestMapping("/sendHtmlWithAttach")
    @ControllerWebLog(name = "查询", intoDb = true)
    public String sendHtmlWithAttach() {
        mailTool.sendHtmlWithAttach();
        return "sendHtmlWithAttach success";
    }
}
