package com.njj.blog.search.service.imp;

import com.njj.blog.feign.dto.BlogDTO;
import com.njj.blog.search.repository.BlogRepository;
import com.njj.blog.search.service.BlogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 15:42
 **/
@Service
public class BlogSearchServiceImp implements BlogSearchService {

    private BlogRepository blogRepository;

    @Override
    public List<BlogDTO> searchBlog(String keyWord) {
        //TODO: 倪佳俊 2023/7/8 22:56 [] 实现分页检索
        // 从Es根据keyWord获取相关文档
        return null;
    }

    @Override
    public void addBlog(BlogDTO blogDTO) {
        //TODO: 倪佳俊 2023/7/8 22:59 [] 保存博客信息到ES
        System.out.println("添加索引！！！");
//        blogRepository.save(blogDTO);
    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
