package com.njj.blog.mapper;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.njj.blog.common.util.common.date.DateUtils;
import com.njj.blog.common.util.database.annotations.GenerateId;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Field;

/**
 * 数据库ID生成器，ID生成与数据源解耦
 *
 * @author 倪佳俊
 * @date 2023/2/12
 */
public class IdGenerator implements IdentifierGenerator {

    private static final int idLength = 32;

    public static <T> T generateId(T someInfo){
        //TODO: 倪佳俊 2023/3/24 23:31 [] 这里的代码是干嘛的？
        if(someInfo == null){
            return someInfo;
        }
        Class<? extends Object> clazz = someInfo.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            GenerateId[] generateIds = field.getAnnotationsByType(GenerateId.class);
            if(generateIds.length != 0){
                field.setAccessible(true);
                try {
                    field.set(someInfo, generateId(idLength));
                } catch (IllegalAccessException e) {
                    //TODO: 倪佳俊 2023/2/12 11:55 [] 日志打印
                    throw new RuntimeException(e);
                }
            }
        }
        return someInfo;
    }

    /**
     * 生成指定长度的ID
     * 生成规则：yyyyMMddHHmmss+（指定长度-14的随机字符）
     *
     * @param idLength 指定长度
     * @return
     */
    private static String generateId(int idLength) {
        String dateString = DateUtils.formatCurrentDate(DateUtils.DATE_FORMAT_DATETIME);
        String randomString = RandomStringUtils.random(idLength - dateString.length());
        return dateString + randomString;
    }

    @Override
    public boolean assignId(Object idValue) {
        return IdentifierGenerator.super.assignId(idValue);
    }

    @Override
    public Number nextId(Object entity) {
        return null;
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
