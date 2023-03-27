package com.njj.blog.mail.service;

import org.thymeleaf.context.Context;

/**
 * 邮箱服务
 */
public interface MailService {

    /**
     * 发送邮件
     * @param mailContent 邮件内容
     */
    //TODO: 倪佳俊 2023/3/9 20:27 [] 扩展该接口，支持html模板邮件
    void sendMail(String mailContent);

    void sendTemplateMail(String templateName, Context context);

}
