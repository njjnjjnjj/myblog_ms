package com.njj.blog.common.config;

/**
 * RabbitMQ公共配置类
 *
 * @author 倪佳俊
 * @date 2023/06/25 17:34
 **/
public class RabbitMQConfig {
    /**
     * 邮箱消息队列主题名
     */
    public static final String MAIL_TOPIC = "mailTopic";

    public static final String MAIL_DIRECT_EXCHANGE = "mailDirectExchange";

    public static final String MAIL_DIRECT_ROUTING = "mailDirectRouting";
}
