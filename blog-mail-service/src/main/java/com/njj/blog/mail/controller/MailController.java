package com.njj.blog.mail.controller;

import com.njj.blog.feign.clients.MailClient;
import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.common.response.ResponseUtil;
import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;
import com.njj.blog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mail")
//@RequestMapping("/mail")
public class MailController implements MailClient {
    private MailService mailService;

//    @PostMapping("/send")
    @Override
    public ResponseResult<String> sendMail(@RequestBody SendMailBlogMetadataDTO blogMetadataDTO){
        //TODO: 倪佳俊 2023/4/6 20:34 [] 发送方式由内部决定
        mailService.sendTemplateMail(blogMetadataDTO);
        //TODO: 倪佳俊 2023/4/6 20:42 [] 修改响应结果
        return ResponseUtil.success("");
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
} 
