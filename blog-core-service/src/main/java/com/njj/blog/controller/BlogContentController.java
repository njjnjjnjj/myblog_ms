package com.njj.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.common.response.ResponseUtil;
import com.njj.blog.entity.BlogMetadataInfo;
import com.njj.blog.entity.BlogRequestBody;
import com.njj.blog.entity.BlogResponseData;
import com.njj.blog.service.BlogContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/2/11
 */

@RestController
@RequestMapping("/blog")
@Api
public class BlogContentController {

    private BlogContentService blogContentService;

    @PostMapping("/content")
    public ResponseResult<Object> saveBlogContent(@RequestBody BlogRequestBody blogRequestBody){
        blogContentService.saveBlogContent(blogRequestBody);
        return ResponseUtil.success();
    }

    @GetMapping("/content/{metadataId}")
    public ResponseResult<BlogResponseData> loadBlogContent(@PathVariable("metadataId") String metadataId){
        BlogResponseData blogResponseData = blogContentService.loadBlogContent(metadataId);
        return ResponseUtil.success(blogResponseData,null);
    }

    @ApiOperation("对指定发布状态的博客进行分页查询")
    @GetMapping("/content/query/{publishMode}")
    public ResponseResult<Page<BlogMetadataInfo>> queryBlog(Page<BlogMetadataInfo> page, @PathVariable("publishMode") String publishMode){
        Page<BlogMetadataInfo> blogResponseDataPage = blogContentService.queryBlogMetadataByPublishMode(page,publishMode);
        return ResponseUtil.success(blogResponseDataPage);
    }

    @ApiOperation("对指定发布状态的博客进行全部查询")
    @GetMapping("/content/query/all/{publishMode}")
    public ResponseResult<List<BlogMetadataInfo>> getAllBlog(@PathVariable("publishMode") String publishMode){
        List<BlogMetadataInfo> allBlogMetadataByPublishMode = blogContentService.getAllBlogMetadataByPublishMode(publishMode);
        return ResponseUtil.success(allBlogMetadataByPublishMode);
    }

    @Autowired
    public void setBlogContentService(BlogContentService blogContentService) {
        this.blogContentService = blogContentService;
    }
}
