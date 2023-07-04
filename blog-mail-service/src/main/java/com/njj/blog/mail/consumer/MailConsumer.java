package com.njj.blog.mail.consumer;

import com.njj.blog.common.config.RabbitMQConfig;
import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;
import com.njj.blog.mail.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 倪佳俊
 * @date 2023/06/25 21:59
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.MAIL_TOPIC)
public class MailConsumer {

    private MailService mailService;

    @RabbitHandler
    public void mailConsumer(SendMailBlogMetadataDTO sendMailBlogMetadataDTO){
        mailService.sendTemplateMail(sendMailBlogMetadataDTO);
//        System.out.println("收到发送邮件请求！"+sendMailBlogMetadataDTO);
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
