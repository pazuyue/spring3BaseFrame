package com.example.commonadvice.config.Mail;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailTool {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     *
     * @return
     */
    public boolean send(String recipient,String subject,String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);//发送者
            message.setTo(recipient);//接受者
            //message.setCc("sohuniuer@sina.com");// 抄送
            message.setSubject(subject); //邮件主题
            message.setText(text);//邮件内容
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送富文本邮件
     *
     * @return
     */
    public boolean sendHtml() {
        // 使用 JavaMail 的 MimeMessage，支持更多复杂的邮件格式和内容
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // 创建 MimeMessageHelper 对象，处理 MimeMessage 辅助类
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 使用辅助类 MimeMessage 设定参数
            helper.setFrom(from);
            helper.setTo("zwqh@clover1314.com");
            helper.setBcc("sohuniuer@sina.com");//密送
            helper.setSubject("富文本邮件主题");
            helper.setText("<h1>这是富文本邮件内容标题</h1><p style='color:red;'>这里是段落一</p><p style='color:orange;'>这里是段落二</p>",
                    true);
            mailSender.send(mimeMessage);
            System.out.println("邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 发送富文本带附件的邮件
     * @return
     */
    public boolean sendHtmlWithAttach() {
        // 使用 JavaMail 的 MimeMessage，支持更多复杂的邮件格式和内容
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            // 创建 MimeMessageHelper 对象，处理 MimeMessage 辅助类
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo("yueguang@qingmutec.com");
            helper.setSubject("富文本带附件的邮件主题");
            helper.setText("<h1>这是富文本邮件内容标题</h1><p style='color:red;'>这里是段落一</p><p style='color:orange;'>这里是段落二</p>",
                    true);
            //加载文件资源作为附件
            ClassPathResource file=new ClassPathResource("static/avatar2.jpg");
            //添加附件,并重命名
            helper.addAttachment("附件.jpg", file);
            mailSender.send(mimeMessage);
            System.out.println("邮件发送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
