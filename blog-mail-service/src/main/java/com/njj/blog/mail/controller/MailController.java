package com.njj.blog.mail.controller;

import com.njj.blog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;

    @GetMapping("/send")
    public void sendMail(){
        mailService.sendMail("just for test");
        Context context = new Context();
        context.setVariable("title","this is title");
        context.setVariable("contentSummary", "this is contentSummary");
        context.setVariable("publishDatetime","this is publishDatetime");
        mailService.sendTemplateMail("NewBlogNotification", context);
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
