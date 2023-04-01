package com.njj.blog.mail.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.common.response.ResponseUtil;
import com.njj.blog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/mail")
public class MailController {

//    @Value("${spring.mail.username}")
    @Value("${test}")
//    @NacosValue("${test}")
    private String testConfig;

    @PostConstruct
    public void init(){
        System.out.println(testConfig);
    }

    private MailService mailService;

    @GetMapping("/send")
    public ResponseResult<Object> sendMail(){
        mailService.sendMail("just for test");
        Context context = new Context();
        context.setVariable("title","this is title");
        context.setVariable("contentSummary", "this is contentSummary");
        context.setVariable("publishDatetime","this is publishDatetime");
        mailService.sendTemplateMail("NewBlogNotification", context);
        return ResponseUtil.success("");
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
