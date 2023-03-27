package com.njj.blog.picture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.njj.blog")
public class PictureBedServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PictureBedServiceApplication.class,args);
    }
}
