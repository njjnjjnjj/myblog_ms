package com.njj.blog.scheduled.publish;

import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.entity.BlogMetadataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class TimedPublishingBlogTask {

    //TODO: 倪佳俊 2023/3/25 9:36 [] 此定时任务 仅处理 Redis 相关操作，定时任务发布以及获取 通过core服务实现
    public static final String PRE_PUBLISHED_BLOG_KEY = "prePublishedBlog";
//
//    private BlogMetadataInfoMapper blogMetadataInfoMapper;
//
//    private BlogContentService blogContentService;
    @Resource
    private RedisTemplate<String,BlogMetadataInfo> redisTemplate;

    private RestTemplate restTemplate;

//
    @PostConstruct
    public void init(){
        loadPrePublishedBlogToRedis();
    }
//
//    /**
//     * 每秒执行一次，从redis中获取定时发布博客进行发布
//     */
//    @Scheduled(fixedDelay = 1000)
//    public void timedPublishingBlog() {
//        ListOperations<String, BlogMetadataInfo> stringBlogMetadataInfoListOperations = redisTemplate.opsForList();
//        Long size = stringBlogMetadataInfoListOperations.size(PRE_PUBLISHED_BLOG_KEY);
////        System.out.println("currentSize:"+size);
//        // size != null 与 null != size 在这里有区别吗？ 没有任何区别！idea 你自作聪明！ 我非要写 size != null
//        if(size != null){
//            for(int i = 0; i < size; i ++){
//                BlogMetadataInfo blogMetadataInfo = stringBlogMetadataInfoListOperations.index(PRE_PUBLISHED_BLOG_KEY, i);
//                //TODO: 倪佳俊 2023/3/8 21:55 [] 发布时间需要有一定的容错
//                blogContentService.publishBlog(blogMetadataInfo.getMetadataId());
//            }
//        }
//    }
//
//
//
    /**
     * redis不做持久化，为保证内容时效性，发布定时博客时，添加至 redis，系统启动时，将已存在的定时博客 保存至 redis
     * 将预发布的文章缓存至 redis
     */
    private void loadPrePublishedBlogToRedis(){
        ListOperations<String, BlogMetadataInfo> stringBlogMetadataInfoListOperations = redisTemplate.opsForList();
        // 清楚redis中原有数据
        redisTemplate.delete(PRE_PUBLISHED_BLOG_KEY);
        // 调用 core 服务，获取所预发布的博客
        ResponseResult<List<BlogMetadataInfo>> result = restTemplate.exchange("http://127.0.0.1:8080/blog/content/query/all/"+BlogMetadataInfo.PUBLISH_MODE_PRE_PUBLISH, HttpMethod.GET, null, new ParameterizedTypeReference<ResponseResult<List<BlogMetadataInfo>>>() {}).getBody();
        List<BlogMetadataInfo> blogMetadataInfos = result.getData();
        if(blogMetadataInfos != null){
            for (BlogMetadataInfo blogMetadataInfo: blogMetadataInfos) {
                stringBlogMetadataInfoListOperations.leftPush(PRE_PUBLISHED_BLOG_KEY,blogMetadataInfo);
            }
        }
    }
//
//    @Autowired
//    public void setBlogMetadataInfoMapper(BlogMetadataInfoMapper blogMetadataInfoMapper) {
//        this.blogMetadataInfoMapper = blogMetadataInfoMapper;
//    }
//
//    @Autowired
//    public void setBlogContentService(BlogContentService blogContentService) {
//        this.blogContentService = blogContentService;
//    }


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
