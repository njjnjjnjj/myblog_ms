package com.njj.blog.mail.consumer;

import com.njj.blog.common.constans.RabbitMQConstant;
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
@RabbitListener(queues = RabbitMQConstant.BLOG_PUBLISH_MAIL_SEND_QUEUE)
public class MailConsumer {

    private MailService mailService;

    @RabbitHandler
    public void mailConsumer(SendMailBlogMetadataDTO sendMailBlogMetadataDTO){
        mailService.sendTemplateMail(sendMailBlogMetadataDTO);
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
