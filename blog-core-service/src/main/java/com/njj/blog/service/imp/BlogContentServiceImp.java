package com.njj.blog.service.imp;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njj.blog.common.constans.RabbitMQConstant;
import com.njj.blog.common.util.common.date.DateUtils;
import com.njj.blog.common.util.common.string.StringUtils;
import com.njj.blog.entity.BlogContentInfo;
import com.njj.blog.entity.BlogMetadataInfo;
import com.njj.blog.entity.BlogRequestBody;
import com.njj.blog.entity.BlogResponseData;
import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;
import com.njj.blog.mapper.BlogContentInfoMapper;
import com.njj.blog.mapper.BlogMetadataInfoMapper;
import com.njj.blog.service.BlogContentService;
import com.njj.blog.task.TimedPublishingBlogTask;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/2/11
 */
@Service
@RefreshScope
public class BlogContentServiceImp implements BlogContentService {
    private BlogContentInfoMapper blogContentInfoMapper;
    private BlogMetadataInfoMapper blogMetadataInfoMapper;

    private RedisTemplate<String, BlogMetadataInfo> redisTemplate;

    private RestTemplate restTemplate;

//    private RabbitTemplate rabbitTemplate;

//    private MailClient mailClient;

    @Value("${blog.mail.sendMail}")
    private boolean sendMail;

    @Override
    public void saveBlogContent(BlogRequestBody blogRequestBody) {
        // 插入博客元数据
        BlogMetadataInfo.BlogMetadataInfoBuilder blogMetadataBuilder = BlogMetadataInfo.builder();
        String currentDatetime = DateUtils.formatCurrentDate(DateUtils.DATE_FORMAT_DATETIME);
        long timeMillis = System.currentTimeMillis();
        Timestamp currentTimeMills = new Timestamp(timeMillis);
        blogMetadataBuilder.publishDatetime(currentTimeMills);
        blogMetadataBuilder.updateDatetime(currentTimeMills);
        //TODO: 倪佳俊 2023/2/12 15:03 [] 获取作者id
        blogMetadataBuilder.authorId("13023468552");
        blogMetadataBuilder.title(blogRequestBody.getTitle());
        blogMetadataBuilder.contentSummary(StringUtils.summarizeString(blogRequestBody.getBlogContent(), BlogMetadataInfo.CONTENT_SUMMARY_LENGTH, BlogMetadataInfo.CONTENT_SUMMARY_SYMBOLS));
        blogMetadataBuilder.visibility(blogRequestBody.getVisibility());
        //TODO: 倪佳俊 2023/2/28 19:36 [] 需要考虑存草稿想的发布状态
        blogMetadataBuilder.publishMode(blogRequestBody.getPublishMode());
        if (BlogMetadataInfo.PUBLISH_MODE_PRE_PUBLISH.equals(blogRequestBody.getPublishMode())) {
            blogMetadataBuilder.prePublishDatetime(new Timestamp(blogRequestBody.getPrePublishDatetime().getTime()));
        }
        BlogMetadataInfo blogMetadataInfo = blogMetadataBuilder.build();
        blogMetadataInfoMapper.insert(blogMetadataInfo);
        String blogMetadataId = blogMetadataInfo.getMetadataId();
        // 保存博客内容
        String blogContent = blogRequestBody.getBlogContent();
        List<String> splitBlogContent = StringUtils.splitString(blogContent, BlogContentInfo.CONTENT_LENGTH_LIMIT);
        long order = 0;
        for (String content : splitBlogContent) {
            BlogContentInfo.BlogContentInfoBuilder builder = BlogContentInfo.builder();
            builder.metadataId(blogMetadataId);
            builder.content(content);
            builder.orderNumber(order);
            blogContentInfoMapper.insert(builder.build());
            order++;
        }
        // 预发布
        if (BlogMetadataInfo.PUBLISH_MODE_PRE_PUBLISH.equals(blogMetadataInfo.getPublishMode())) {
            addPrePublishBlogToRedis(blogMetadataInfo);
        }
        // 发布
        if (BlogMetadataInfo.PUBLISH_MODE_PUBLISHED.equals(blogMetadataInfo.getPublishMode())) {
            //TODO: 倪佳俊 2023/3/30 22:56 [] 这里存在，数据库的重复操作
            publishBlog(blogMetadataId);
        }
    }

    @Override
    public BlogResponseData loadBlogContent(String blogMetadataId) {
        // 查询博客元数据 并装载
        BlogMetadataInfo blogMetadataInfo = blogMetadataInfoMapper.selectById(blogMetadataId);
        if (blogMetadataInfo == null) {
            return null;
        }
        BlogResponseData blogResponseData = new BlogResponseData();
        BeanUtils.copyProperties(blogMetadataInfo, blogResponseData);
        //TODO: 倪佳俊 2023/2/18 10:39 [待用户表创建完成] 从用户表中提取用户姓名
//        dataBuilder.author(blogMetadataInfo.getAuthorId());
        // 查询并装载博客内容
        String blogContent = getBlogContentByMetadataId(blogMetadataId);
        blogResponseData.setBlogContent(blogContent);
        return blogResponseData;
    }

    @Override
    public Page<BlogMetadataInfo> queryBlogMetadataByPublishMode(Page<BlogMetadataInfo> page, String publishMode) {
        LambdaQueryWrapper<BlogMetadataInfo> blogMetadataInfoLambdaQueryWrapper = new LambdaQueryWrapper<BlogMetadataInfo>();
        blogMetadataInfoLambdaQueryWrapper.eq(BlogMetadataInfo::getPublishMode, publishMode);
        // 如果orders为空，则设置按照时间倒叙排序
        if (page.orders().isEmpty()) {
            blogMetadataInfoLambdaQueryWrapper.orderByDesc(BlogMetadataInfo::getPublishDatetime);
        }
        return blogMetadataInfoMapper.selectPage(page, blogMetadataInfoLambdaQueryWrapper);
    }

    @Override
    public void publishBlog(String blogMetaDataId) {
        // 更新博客状态以及发布时间
        BlogMetadataInfo blogMetadataInfo = blogMetadataInfoMapper.selectById(blogMetaDataId);
        blogMetadataInfo.setPublishDatetime(new Timestamp(System.currentTimeMillis()));
        blogMetadataInfo.setPublishMode(BlogMetadataInfo.PUBLISH_MODE_PUBLISHED);
        blogMetadataInfoMapper.updateById(blogMetadataInfo);
        // 调用mail服务，发送邮件
        if (sendMail) {
            SendMailBlogMetadataDTO blogMetadataDTO = SendMailBlogMetadataDTO.builder().
                    title(blogMetadataInfo.getTitle()).
                    contentSummary(blogMetadataInfo.getContentSummary()).
                    publishDatetime(DateUtils.formatDate(new Date(blogMetadataInfo.getPublishDatetime().getTime()), DateUtils.DATE_FORMAT_DATETIME)).
                    build();
            // 使用消息队列实现异步调用
//            rabbitTemplate.convertAndSend(RabbitMQConstant.BLOG_PUBLISH_FANOUT_EXCHANGE,"",blogMetadataDTO);
        }
    }

    @Override
    public List<BlogMetadataInfo> getAllBlogMetadataByPublishMode(String publishMode) {
        LambdaQueryWrapper<BlogMetadataInfo> blogMetadataInfoLambdaQueryWrapper = new LambdaQueryWrapper<BlogMetadataInfo>();
        blogMetadataInfoLambdaQueryWrapper.eq(BlogMetadataInfo::getPublishMode, publishMode);
        List<BlogMetadataInfo> blogMetadataInfos = blogMetadataInfoMapper.selectList(blogMetadataInfoLambdaQueryWrapper);
        return blogMetadataInfos;
    }

    /**
     * 将预发布的博客保存至 redids
     *
     * @param blogMetadataInfo 预发布的博客元数据
     */
    public void addPrePublishBlogToRedis(BlogMetadataInfo blogMetadataInfo) {
        ListOperations<String, BlogMetadataInfo> stringBlogMetadataInfoListOperations = redisTemplate.opsForList();
        stringBlogMetadataInfoListOperations.leftPush(TimedPublishingBlogTask.PRE_PUBLISHED_BLOG_KEY, blogMetadataInfo);
    }

    private String getBlogContentByMetadataId(String metadataId) {
        QueryWrapper<BlogContentInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("metadata_id", metadataId)
                .orderByAsc("order_number");
        List<BlogContentInfo> blogContentInfos = blogContentInfoMapper.selectList(wrapper);
        StringBuilder blogContent = new StringBuilder();
        for (BlogContentInfo blogContentInfo : blogContentInfos) {
            String content = blogContentInfo.getContent();
            blogContent.append(content);
        }
        return blogContent.toString();
    }

    @Autowired
    public void setBlogContentInfoMapper(BlogContentInfoMapper blogContentInfoMapper) {
        this.blogContentInfoMapper = blogContentInfoMapper;
    }

    @Autowired
    public void setBlogMetadataInfoMapper(BlogMetadataInfoMapper blogMetadataInfoMapper) {
        this.blogMetadataInfoMapper = blogMetadataInfoMapper;
    }

    @Resource
    public void setRedisTemplate(RedisTemplate<String, BlogMetadataInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Autowired
//    public void setMailClient(MailClient mailClient) {
//        this.mailClient = mailClient;
//    }

//    @Autowired
//    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
}
