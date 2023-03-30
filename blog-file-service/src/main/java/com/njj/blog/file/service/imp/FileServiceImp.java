package com.njj.blog.file.service.imp;

import com.njj.blog.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Service
public class FileServiceImp implements FileService {

    @Value("${fileStore.savePath:D:\\FileStore}")
    private String fileStoreSavePath;

    @Override
    public String uploadFile(InputStream in) {
        //TODO: 倪佳俊 2023/3/30 22:06 [] 将文件保存至存储路径下，并生成对应路径，并返还文件id
        try {
            int available = in.available();
            ByteBuffer byteBuffer = ByteBuffer.allocate(available);

            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
