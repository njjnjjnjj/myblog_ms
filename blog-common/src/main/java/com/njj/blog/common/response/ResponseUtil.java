package com.njj.blog.common.response;

import com.njj.blog.common.response.sensitive.SensitiveData;
import com.njj.blog.common.response.sensitive.SensitiveField;
import com.njj.blog.common.response.sensitive.SensitiveFieldHandleMethod;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author 倪佳俊
 * @date 2023/2/18
 */
public class ResponseUtil {

    /**
     * 成功不返还任何数据
     *
     * @return 返还带有时间戳和状态码的响应实体
     */
    public static <T> ResponseResult<T> success() {
        return success(null, null);
    }

    /**
     * @param data 需要返还给前台的数据
     * @param <T>  data的类型
     * @return 返还装有数据的响应实体
     */
    public static <T> ResponseResult<T> success(T data) {
        return success(data, null);
    }

    /**
     * 成功返还数据与信息
     *
     * @param data    需要返还给前台的数据
     * @param message 需要返还给前台的消息
     * @param <T>     data的类型
     * @return 返还装有数据的响应实体
     */
    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .message(StringUtils.isEmpty(message) ? ResponseStatus.SUCCESS.getDescription() : message)
                .data(handleSensitiveData(data)).build();
    }

    /**
     * 失败
     *
     * @return 返还携带有时间戳与状态码的 响应实体
     */
    public static <T> ResponseResult<T> fail() {
        return fail(null, null);
    }

    /**
     * 失败
     *
     * @param message 错误信息
     * @param <T>     在这里没什么用的泛型
     * @return 返还失败的响应体
     */
    public static <T> ResponseResult<T> fail(String message) {
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.FAIL.getResponseCode())
                .message(StringUtils.isEmpty(message) ? ResponseStatus.FAIL.getDescription() : message)
                .build();
    }

    /**
     * 失败
     *
     * @param data    需要携带的数据
     * @param message 需要携带的消息
     * @param <T>     data的类型
     * @return 返还失败的响应实体
     */
    public static <T> ResponseResult<T> fail(T data, String message) {
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.FAIL.getResponseCode())
                .message(StringUtils.isEmpty(message) ? ResponseStatus.FAIL.getDescription() : message)
                .data(data).build();
    }

    /**
     * 处理敏感数据
     *
     * @param data 需要处理的数据
     * @param <T> 数据类型
     * @return 处理后的数据
     */
    private static <T> T handleSensitiveData(T data) {
        // 如果是null，则直接返回null
        if(data == null){
            return null;
        }
        // 如果T是集合，则遍历所有元素
        if(data instanceof Iterable){
            Iterable<?> iterable = (Iterable<?>) data;
            for (Object o : iterable) {
                handleSensitiveData(o);
            }
        }
        // 通过反射获取data的类型，并且获取类头部的SensitiveData注解
        Class<?> dataClazz = data.getClass();
        SensitiveData annotation = dataClazz.getAnnotation(SensitiveData.class);
        if(annotation != null){
            Field[] declaredFields = dataClazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                SensitiveField sensitiveField = declaredField.getAnnotation(SensitiveField.class);
                if(sensitiveField != null){
                    // 如果字段上有SensitiveField注解，则将该字段置空
                    if(sensitiveField.value().equals(SensitiveFieldHandleMethod.SET_NULL)){
                        declaredField.setAccessible(true);
                        try {
                            declaredField.set(data, null);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    //TODO: 倪佳俊 2023/12/7 17:41 [] 补充其他处理方式
                }
            }
        }
        return data;
    }


}
