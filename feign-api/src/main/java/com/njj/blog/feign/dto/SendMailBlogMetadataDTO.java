package com.njj.blog.feign.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 发送邮件DTO
 */
@Data
@Builder
public class SendMailBlogMetadataDTO {
    /**
     * 博客标题
     */
    private String title;
    /**
     * 博客摘要内容
     */
    private String contentSummary;
    /**
     * 博客发送时间
     */
    private String publishDatetime;
}
