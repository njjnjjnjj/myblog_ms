package com.njj.blog.file.controller;

import com.njj.blog.common.response.ResponseResult;
import com.njj.blog.common.response.ResponseUtil;
import com.njj.blog.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController("/file")
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult<String> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        //TODO: 倪佳俊 2023/3/30 22:08 [] 异常处理
        try {
            InputStream inputStream = uploadFile.getInputStream();
            String fileId = fileService.uploadFile(inputStream);
            return ResponseUtil.success(fileId);
        } catch (IOException e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
