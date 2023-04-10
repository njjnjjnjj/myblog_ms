package com.njj.blog.common.util.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeUtils {

    /**
     * 生成发送二维码方法
     *
     * @param text     二维码生成规则(二维码可以是任何英文字母加数字生成的二维码)
     * @param width    宽度
     * @param height   高度
     * @param filePath 输出图片地址
     */
    public static void getQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        //生成二维码类
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //生成的二维码
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        //生成图片唯一名称,加上.png格式
        String pat = "qrcode.png";
        //图片路劲加上图片名称  (输出地址)
        filePath += pat;
        Path path = FileSystems.getDefault().getPath(filePath);
        //输出二维码图片
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) throws IOException, WriterException {
        getQRCodeImage("hello world!",300,300,"D:\\QRCODE");
    }

}
