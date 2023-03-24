package com.njj.blog.entity;

import lombok.Data;

/**
 * 博客内容等
 * @author 倪佳俊
 * @date 2023/2/18
 */
@Data
public class BlogResponseData extends BlogMetadataInfo{
//
    public BlogResponseData(){
        super();
    }
//    private String title;
    private String blogContent;
//    private String publishDatetime;
//
//    private String author;
//    private String visibility;
//    private String accessPassword;
//    private String publishMode;
//    private String prePublishDatetime;
}
