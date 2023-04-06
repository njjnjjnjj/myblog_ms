package com.njj.blog.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("blog-mail-service")
public interface MailClient extends com.njj.blog.feign.clients.MailClient {

}
