package com.qianjing.collect.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String me;

    public String sendCode(String receive, String subject) {
        String code = randomCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText("验证码为：" + code);
        message.setFrom(me);
        message.setTo(receive);
        mailSender.send(message);
        return code;
    }

    public void sendText(String receive, String subject,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(me);
        message.setTo(receive);
        mailSender.send(message);
    }

    //产生6位的随机验证码
    private String randomCode() {
        //100000-999999
        int code = (int) (Math.random() * 900000 + 100000);
        return String.valueOf(code);
    }

    public void sendMime(String receive, String subject, String content,File file){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setSubject(subject);
            helper.setText(content);
            FileSystemResource fsr = new FileSystemResource(file);
            helper.addAttachment(Objects.requireNonNull(fsr.getFilename()),fsr);
            helper.setTo(receive);
            helper.setFrom(me);
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }




}
