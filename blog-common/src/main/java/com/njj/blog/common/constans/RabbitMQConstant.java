package com.njj.blog.common.constans;

/**
 * RabbitMQ公共配置类
 *
 * @author 倪佳俊
 * @date 2023/06/25 17:34
 **/
public class RabbitMQConstant {
//    /**
//     * 邮箱消息队列主题名
//     */
//    public static final String MAIL_TOPIC = "mailTopic";
//
//    public static final String MAIL_DIRECT_EXCHANGE = "mailDirectExchange";
//
//    public static final String MAIL_DIRECT_ROUTING = "mailDirectRouting";
//
//    /**
//     * 博客索引同步消息队列
//     */
//    public static final String BLOG_INDEX_SYNC_QUEUE = "blogIndexSyncQueue";


    /**
     * 博客发布 广播 交换机
     */
    public static final String BLOG_PUBLISH_FANOUT_EXCHANGE = "blogPublishFanoutExchange";

    /**
     * 邮件发送消息队列
     */
    public static final String BLOG_PUBLISH_MAIL_SEND_QUEUE = "blogPublishMailSendQueue";

    /**
     * 博客索引添加消息队列
     */
    public static final String BLOG_PUBLISH_INDEX_ADD_QUEUE = "blogPublishIndexAddQueue";
}
