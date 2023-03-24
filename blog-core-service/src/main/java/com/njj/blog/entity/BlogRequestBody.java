package com.njj.blog.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author 倪佳俊
 * @date 2023/2/12
 */
@Data
@Builder
public class BlogRequestBody {
    private String title;
    private String blogContent;
    private String visibility;
    private String accessPassword;
    private String publishMode;
    private Date prePublishDatetime;
}
