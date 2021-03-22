package com.example.springboot1task;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("紧急通知！！！");
        smm.setText("猪肉降价了！！！");
        smm.setTo("18629335734@163.com");
        smm.setFrom("2836428900@qq.com");
        mailSender.send(smm);
    }

    @Test
    public void test2() throws MessagingException {
        //创建一个复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("严正声明！！！");
        mimeMessageHelper.setText("<b style='color:red'>今年必须脱单！！！</b>",true);
        mimeMessageHelper.setTo("18629335734@163.com");
        mimeMessageHelper.setFrom("2836428900@qq.com");

        //上传文件
        mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\soshuai\\Desktop\\图片\\1.jpg"));
        mimeMessageHelper.addAttachment("2.jpg",new File("C:\\Users\\soshuai\\Desktop\\图片\\2.jpg"));

        mailSender.send(mimeMessage);
    }

}
