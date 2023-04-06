package com.njj.blog.mail.service;

import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;

/**
 * 邮箱服务
 */
public interface MailService {

    /**
     * 发送邮件
     * @param mailContent 邮件内容
     */
    @Deprecated
    void sendMail(String mailContent);

    void sendTemplateMail(SendMailBlogMetadataDTO blogMetadataDTO);

}
