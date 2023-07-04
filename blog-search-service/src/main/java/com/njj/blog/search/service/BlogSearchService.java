package com.njj.blog.search.service;

import com.njj.blog.feign.dto.BlogDTO;
import com.njj.blog.search.entity.Blog;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 15:41
 **/
public interface BlogSearchService {

    /**
     * 从Es中根据keyWord获取博客内容
     * @param keyWord
     */
    List<BlogDTO> searchBlog(String keyWord);

    /**
     * 向Es中新增博客
     * @param blogDTO
     */
    void addBlog(BlogDTO blogDTO);

}
