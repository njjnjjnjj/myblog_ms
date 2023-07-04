package com.njj.blog.search.repository;

import com.njj.blog.search.entity.Blog;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/07/03 16:08
 **/
@Repository
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

    @Highlight(fields = {
            @HighlightField(name = "content")
    })
    List<Blog> findBlogByContentLike(String content);

    List<Blog> findBlogByContentLikeOrTitleLike(String title, String content);

}
