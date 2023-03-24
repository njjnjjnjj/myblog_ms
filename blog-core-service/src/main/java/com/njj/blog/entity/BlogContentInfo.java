package com.njj.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 倪佳俊
 * @since 2023-02-12
 */
@Data
@Builder
@TableName("t_blog_content")
public class BlogContentInfo{
    /**
     * 博客内容长度限制
     */
    public static Integer CONTENT_LENGTH_LIMIT = 4000;

    @TableId(type = IdType.ASSIGN_UUID)
    private String contentId;

    private String metadataId;

    private Long orderNumber;

    private String content;
}
