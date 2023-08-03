package com.njj.blog.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.njj.blog.entity.BlogMetadataInfo;
import com.njj.blog.mapper.BlogMetadataInfoMapper;
import com.njj.blog.service.BlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


//@Component
public class TimedPublishingBlogTask {
    public static final String PRE_PUBLISHED_BLOG_KEY = "prePublishedBlog";

    private BlogMetadataInfoMapper blogMetadataInfoMapper;

    private BlogContentService blogContentService;
    @Resource
    private RedisTemplate<String, BlogMetadataInfo> redisTemplate;

    @PostConstruct
    public void init(){
        loadPrePublishedBlogToRedis();
    }

    /**
     * 每秒执行一次，从redis中获取定时发布博客进行发布
     */
    @Scheduled(fixedDelay = 1000)
    public void timedPublishingBlog() {
        ListOperations<String, BlogMetadataInfo> stringBlogMetadataInfoListOperations = redisTemplate.opsForList();
        Long size = stringBlogMetadataInfoListOperations.size(PRE_PUBLISHED_BLOG_KEY);
        if(size != null){
            for(int i = 0; i < size; i ++){
                BlogMetadataInfo blogMetadataInfo = stringBlogMetadataInfoListOperations.index(PRE_PUBLISHED_BLOG_KEY, i);
                //TODO: 倪佳俊 2023/3/8 21:55 [] 发布时间需要有一定的容错
                assert blogMetadataInfo != null;
                blogContentService.publishBlog(blogMetadataInfo.getMetadataId());
            }
        }
    }

    /**
     * redis不做持久化，为保证内容时效性，发布定时博客时，添加至 redis，系统启动时，将已存在的定时博客 保存至 redis
     * 将预发布的文章缓存至 redis
     */
    private void loadPrePublishedBlogToRedis(){
        ListOperations<String, BlogMetadataInfo> stringBlogMetadataInfoListOperations = redisTemplate.opsForList();
        // 清楚redis中原有数据
        redisTemplate.delete(PRE_PUBLISHED_BLOG_KEY);
        // 从数据库读取定时博客保存至 redis
        LambdaQueryWrapper<BlogMetadataInfo> blogMetadataInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogMetadataInfoLambdaQueryWrapper.eq(BlogMetadataInfo::getPublishMode,BlogMetadataInfo.PUBLISH_MODE_PRE_PUBLISH);
        List<BlogMetadataInfo> blogMetadataInfos = blogMetadataInfoMapper.selectList(blogMetadataInfoLambdaQueryWrapper);
        for (BlogMetadataInfo blogMetadataInfo: blogMetadataInfos) {
            stringBlogMetadataInfoListOperations.leftPush(PRE_PUBLISHED_BLOG_KEY,blogMetadataInfo);
        }
    }

    @Autowired
    public void setBlogMetadataInfoMapper(BlogMetadataInfoMapper blogMetadataInfoMapper) {
        this.blogMetadataInfoMapper = blogMetadataInfoMapper;
    }

    @Autowired
    public void setBlogContentService(BlogContentService blogContentService) {
        this.blogContentService = blogContentService;
    }
}
