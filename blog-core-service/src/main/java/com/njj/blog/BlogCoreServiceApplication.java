package com.njj.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.njj.blog") // spring ioc 扫包配置
@MapperScan("com.njj.blog.mapper") // mybatis 扫包配置
public class BlogCoreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogCoreServiceApplication.class,args);
    }
}
