package com.njj.blog.search;

import com.njj.blog.search.entity.Blog;
import com.njj.blog.search.repository.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 16:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

    @Resource
    private BlogRepository blogRepository;

    @Test
    public void addBlog(){
        Blog blog = Blog.builder().id("1").content("你好世界，我是倪某").title("你好世界").build();
        blogRepository.save(blog);
    }

    @Test
    public void findBlogByContentLikeOrTitleLike(){
        List<Blog> blogByContentLikeOrTitle = blogRepository.findBlogByContentLikeOrTitleLike("你好","世界");

        for (Blog b :
                blogByContentLikeOrTitle) {
            System.out.println(b);
        }
    }
}
