package com.njj.blog.config;

import com.njj.blog.common.constans.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类，定义队列与交换机以及它们的绑定关系
 *
 * @author 倪佳俊
 * @date 2023/06/25 21:46
 **/
@Configuration
@EnableRabbit
public class RabbitMQConfig implements BeanPostProcessor{

    private RabbitAdmin rabbitAdmin;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //定义交换机
        //定义博客发布广播交换机
        rabbitAdmin.declareExchange(blogPublishFanoutExchange());

        //定义队列
        //邮件发送消息队列
        rabbitAdmin.declareQueue(blogPublishMailSendQueue());
        //索引同步消息队列
        rabbitAdmin.declareQueue(blogPublishIndexAddQueue());

        //定义交换机与队列绑定
        Binding blogPublishMailSendBinding = BindingBuilder
                .bind(blogPublishMailSendQueue())
                .to(blogPublishFanoutExchange());
        rabbitAdmin.declareBinding(blogPublishMailSendBinding);
        Binding blogPublishIndexAddBinding = BindingBuilder
                .bind(blogPublishIndexAddQueue())
                .to(blogPublishFanoutExchange());
        rabbitAdmin.declareBinding(blogPublishIndexAddBinding);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Bean
    public FanoutExchange blogPublishFanoutExchange(){
        // 声明邮件广播交换机
        return new FanoutExchange(RabbitMQConstant.BLOG_PUBLISH_FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue blogPublishMailSendQueue(){
        // 定义邮件发送消息队列
        return new Queue(RabbitMQConstant.BLOG_PUBLISH_MAIL_SEND_QUEUE,true,false,false);
    }

    @Bean
    public Queue blogPublishIndexAddQueue(){
        // 定义添加索引消息队列
        return new Queue(RabbitMQConstant.BLOG_PUBLISH_INDEX_ADD_QUEUE,true,false,false);
    }

    @Autowired
    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }
}
