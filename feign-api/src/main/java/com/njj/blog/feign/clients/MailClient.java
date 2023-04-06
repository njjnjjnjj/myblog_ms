package com.njj.blog.feign.clients;

import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.feign.dto.SendMailBlogMetadataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("blog-mail-service")
public interface MailClient {
    @PostMapping("/mail/send")
    ResponseResult<String> sendMail(@RequestBody SendMailBlogMetadataDTO blogMetadataDTO);
}
