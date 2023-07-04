package com.njj.blog.search.service.imp;

import com.njj.blog.search.entity.Blog;
import com.njj.blog.search.repository.BlogRepository;
import com.njj.blog.search.service.BlogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 倪佳俊
 * @date 2023/07/03 15:42
 **/
@Service
public class BlogSearchServiceImp implements BlogSearchService {

    private BlogRepository blogRepository;

    @Override
    public void searchBlog(String keyWord) {
        // 从Es根据keyWord获取相关文档
        List<Blog> blogByContentLikeOrTitleLike = blogRepository.findBlogByContentLikeOrTitleLike(keyWord, keyWord);

    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
