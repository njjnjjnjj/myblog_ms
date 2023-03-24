package com.njj.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njj.blog.entity.BlogMetadataInfo;
import com.njj.blog.entity.BlogRequestBody;
import com.njj.blog.entity.BlogResponseData;


/**
 * @author 倪佳俊
 * @date 2023/2/11
 */
public interface BlogContentService {
    /**
     * 保存博客内容
     *
     * @param blogContentInfo 博客内容
     */
    void saveBlogContent(BlogRequestBody blogContentInfo);

    /**
     * 获取博客内容
     *
     * @param blogMetadataId 博客元数据id
     * @return 返还通过 blogMetadataId 找到的博客元数据以及内容
     */
    BlogResponseData loadBlogContent(String blogMetadataId);

    /**
     * 分页检索 博客
     *
     * @param page 分页参数
     * @return 返还根据分页参数查询到的结果
     */
    Page<BlogMetadataInfo> queryBlogMetadataByPublishMode(Page<BlogMetadataInfo> page, String publishMode);

    /**
     * 发布博客
     *
     * @param blogMetaDataId 博客元数据ID
     */
    void publishBlog(String blogMetaDataId);
}
