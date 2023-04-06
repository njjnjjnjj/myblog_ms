package com.njj.blog.mail.service.imp;

import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;
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

    @Value("${blog.mail.newBlogMailTemplate}")
    private String newBlogMailTemplate;


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
    public void sendTemplateMail(SendMailBlogMetadataDTO blogMetadataDTO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            //TODO: 倪佳俊 2023/4/6 20:41 [] 从数据库中获取订阅者列表，并发送邮件
            helper.setTo("njj1108@outlook.com");
            //TODO: 倪佳俊 2023/4/6 20:41 [] 这里的subject内容或为配置文件或取博客title
            helper.setSubject(blogMetadataDTO.getTitle());
            // 构造Context并解析邮件模板
            Context context = new Context();
            context.setVariable("title",blogMetadataDTO.getTitle());
            context.setVariable("contentSummary", blogMetadataDTO.getContentSummary());
            context.setVariable("publishDatetime",blogMetadataDTO.getPublishDatetime());
            String text = templateEngine.process(newBlogMailTemplate, context);
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
