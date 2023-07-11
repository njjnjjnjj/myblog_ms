package com.njj.blog.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 博客发布后的切面，织入博客发布的业务，用于将消息发送至消息队列：用于发送邮件以及索引同步
 *
 * @author 倪佳俊
 * @date 2023/07/07 11:24
 **/
//@Aspect
//@Component
public class BlogPublishAspect {
    //TODO: 倪佳俊 2023/7/8 23:14 [] 使用aop
//    @AfterReturning
//    public void blogPublished(){
//
//    }

}
