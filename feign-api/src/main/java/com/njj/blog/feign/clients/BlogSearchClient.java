package com.njj.blog.feign.clients;

import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.feign.dto.BlogDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 15:35
 **/
public interface BlogSearchClient {

    @GetMapping("/blog/search")
    ResponseResult<List<BlogDTO>> searchBlog(String keyword);

    @PostMapping("/blog/add")
    ResponseResult<String> addBlog(BlogDTO blogDTO);

}
