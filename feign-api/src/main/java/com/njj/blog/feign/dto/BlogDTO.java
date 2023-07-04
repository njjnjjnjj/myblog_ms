package com.njj.blog.feign.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 *
 * @author 倪佳俊
 * @date 2023/07/04 14:24
 **/
@Data
@Builder
public class BlogDTO {
    private String id;
    private String title;
    private String content;
}
