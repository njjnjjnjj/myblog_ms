package com.njj.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.njj.blog")
public class BlogFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogFileServiceApplication.class, args);
    }
}
