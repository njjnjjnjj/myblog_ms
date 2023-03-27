package com.njj.blog.mail.service.imp;

import com.njj.blog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImp implements MailService {

    private JavaMailSender javaMailSender;

    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;


    @Override
    public void sendMail(String mailContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("test");
        message.setText(mailContent);
        message.setTo("njj1108@outlook.com");
        message.setFrom(username);
        javaMailSender.send(message);


    }

    @Override
    public void sendTemplateMail(String templateName, Context context) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo("njj1108@outlook.com");
            helper.setSubject("just test");
            // 解析邮件模板
            String text = templateEngine.process(templateName, context);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
