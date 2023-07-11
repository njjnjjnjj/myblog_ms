package com.njj.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.njj.blog") // spring ioc 扫包配置
@MapperScan("com.njj.blog.mapper") // mybatis 扫包配置
//@EnableFeignClients(basePackages = "com.njj.blog.clients")
public class BlogCoreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogCoreServiceApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        //TODO: 倪佳俊 2023/3/30 22:58 [] 定义超时时间等参数
        return new RestTemplate();
    }
}
