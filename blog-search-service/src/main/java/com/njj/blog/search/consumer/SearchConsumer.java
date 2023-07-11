package com.njj.blog.search.consumer;

import com.njj.blog.common.constans.RabbitMQConstant;
import com.njj.blog.feign.dto.BlogDTO;
import com.njj.blog.search.service.BlogSearchService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 倪佳俊
 * @date 2023/07/08 23:01
 **/
@Component
@RabbitListener(queues = RabbitMQConstant.BLOG_PUBLISH_INDEX_ADD_QUEUE)
public class SearchConsumer {

    private BlogSearchService blogSearchService;

    @RabbitHandler
    public void addIndex(BlogDTO blogDTO){
        blogSearchService.addBlog(blogDTO);
    }

    @Autowired
    public void setBlogSearchService(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }
}
