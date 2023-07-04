package com.njj.blog.search.controller;

import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.common.response.ResponseUtil;
import com.njj.blog.feign.clients.BlogSearchClient;
import com.njj.blog.feign.dto.BlogDTO;
import com.njj.blog.search.entity.Blog;
import com.njj.blog.search.service.BlogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 15:34
 **/
@RestController
public class BlogSearchController implements BlogSearchClient {

    private BlogSearchService blogSearchService;

    @Override
    public ResponseResult<List<BlogDTO>> searchBlog(String keyword) {
        ResponseUtil.success(blogSearchService.searchBlog(keyword));
    }

    @Override
    public ResponseResult<String> addBlog(BlogDTO blogDTO) {

        return null;
    }

    @Autowired
    public void setBlogSearchService(BlogSearchService blogSearchService) {
        this.blogSearchService = blogSearchService;
    }
}
