package com.njj.blog.service.imp;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class BlogContentServiceImpTest {

    private final BlogContentServiceImp blogContentServiceImp;

    @Autowired
    BlogContentServiceImpTest(BlogContentServiceImp blogContentServiceImp) {
        this.blogContentServiceImp = blogContentServiceImp;
    }

    @Test
    void importBlog() throws IOException {
        File targetDir = new File("C:\\Users\\njj99\\Desktop\\wordpress-export-to-markdown\\target\\");
        importBlog(targetDir);
    }

    /**
     * 获取指定文件夹下的所有md文件包含多层文件夹 ，返回File对象集合
     */
    private void importBlog(File targetDir) throws IOException {
        File[] files = targetDir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                importBlog(file);
            } else {
                String fileName = file.getName();
                if (fileName.endsWith(".md")) {
                    String blogContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                    System.out.println(blogContent);
                    blogContentServiceImp.importBlog(blogContent);
                }
            }
        }
    }
}