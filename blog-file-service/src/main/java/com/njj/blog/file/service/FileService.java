package com.njj.blog.file.service;

import java.io.InputStream;

public interface FileService {


    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @return 文件id
     */
    public String uploadFile(InputStream inputStream);
}
