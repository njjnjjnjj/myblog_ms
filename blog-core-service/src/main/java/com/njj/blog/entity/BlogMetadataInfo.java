package com.njj.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.njj.blog.common.response.sensitive.SensitiveData;
import com.njj.blog.common.response.sensitive.SensitiveField;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author 倪佳俊
 * @since 2023-02-12
 */
@Data
@Builder
@TableName("t_blog_metadata")
@SensitiveData
public class BlogMetadataInfo implements Serializable{
    /**
     * 发布状态 —— 已发布
     */
    public static final String PUBLISH_MODE_PUBLISHED = "published";
    /**
     * 发布状态 —— 预发布（指定了发布时间，等待发布）
     */
    public static final String PUBLISH_MODE_PRE_PUBLISH = "prePublish";
    /**
     * 发布状态 —— 未发布
     */
    public static final String PUBLISH_MODE_SKETCH = "sketch";

    /**
     * 发布状态 —— 已回收
     */
    public static final String PUBLISH_MODE_RECYCLED = "recycled";

    /**
     * 可见性 —— 公开
     */
    public static final String VISIBILITY_PUBLIC = "public";
    /**
     * 可见性 —— 私密
     */
    public static final String VISIBILITY_PRIVATE = "private";
    /**
     * 可见性 —— 受密码保护的
     */
    public static final String VISIBILITY_PROTECTED = "protected";
    /**
     * 内容摘要长度
     */
    public static final Integer CONTENT_SUMMARY_LENGTH = 300;
    /**
     * 内容摘要符号
     */
    public static final String CONTENT_SUMMARY_SYMBOLS = "...";

    @Tolerate
    public BlogMetadataInfo() {}

    @TableId(type = IdType.ASSIGN_UUID)
    private String metadataId;
    private Timestamp publishDatetime;
    private Timestamp updateDatetime;
    private String authorId;
    private String title;
    private String contentSummary;
    @SensitiveField
    private String publishMode;
    @SensitiveField
    private String visibility;
    @SensitiveField
    private String accessPassword;
    @SensitiveField
    private Timestamp prePublishDatetime;
}
