package com.njj.blog.common.util.database;

import com.njj.blog.common.util.common.date.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 数据库工具类
 *
 * @author 倪佳俊
 * @date 2023/2/12
 */
public class DatabaseUtils {

    private static final int idLength = 32;

    /**
     * 生成指定长度的ID
     * 生成规则：yyyyMMddHHmmss+（指定长度-14的随机字符）
     *
     * @param idLength 指定长度
     * @return
     */
    public static String generateIdByLength(int idLength) {
        String dateString = DateUtils.formatCurrentDate(DateUtils.DATE_FORMAT_DATETIME_SIMPLE);
        String randomString = RandomStringUtils.random(idLength - dateString.length(),"qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890");
        return dateString + randomString;
    }

    /**
     * 生成ID
     *
     * @return 返还默认长度的ID
     */
    public static String generateId(){
        return generateIdByLength(idLength);
    }
}
