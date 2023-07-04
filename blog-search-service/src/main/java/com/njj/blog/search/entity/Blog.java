package com.njj.blog.search.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 倪佳俊
 * @date 2023/07/03 14:50
 **/
@Data
@Builder
@Document(indexName = "blog")
public class Blog {

    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
}
