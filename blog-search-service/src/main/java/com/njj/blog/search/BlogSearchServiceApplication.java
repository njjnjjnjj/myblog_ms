package com.njj.blog.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 倪佳俊
 * @date 2023/07/03 14:03
 **/
@SpringBootApplication(scanBasePackages = "com.njj.blog.search")
public class BlogSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogSearchServiceApplication.class, args);
    }
}
