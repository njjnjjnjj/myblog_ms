package com.njj.blog.common.response;

import org.springframework.util.StringUtils;

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
    public static <T> ResponseResult<T> success(){
        return success(null,null);
    }

    /**
     *
     * @param data 需要返还给前台的数据
     * @return 返还装有数据的响应实体
     * @param <T> data的类型
     */
    public static <T> ResponseResult<T> success(T data){
        return success(data,null);
    }

    /**
     * 成功返还数据与信息
     *
     * @param data 需要返还给前台的数据
     * @param message 需要返还给前台的消息
     * @return 返还装有数据的响应实体
     * @param <T> data的类型
     */
    public static <T> ResponseResult<T> success(T data, String message){
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .message(StringUtils.isEmpty(message)?ResponseStatus.SUCCESS.getDescription() : message)
                .data(data).build();
    }

    /**
     * 失败
     *
     * @return 返还携带有时间戳与状态码的 响应实体
     */
    public static <T> ResponseResult<T> fail(){
        return fail(null,null);
    }

    /**
     * 失败
     *
     * @param message 错误信息
     * @return 返还失败的响应体
     * @param <T> 在这里没什么用的泛型
     */
    public static <T> ResponseResult<T> fail(String message){
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.FAIL.getResponseCode())
                .message(StringUtils.isEmpty(message)?ResponseStatus.FAIL.getDescription() : message)
                .build();
    }

    /**
     * 失败
     *
     * @param data 需要携带的数据
     * @param message 需要携带的消息
     * @return 返还失败的响应实体
     * @param <T> data的类型
     */
    public static <T> ResponseResult<T> fail(T data, String message){
        return new ResponseResult.ResponseResultBuilder<T>()
                .timestamp(new Date().getTime())
                .status(ResponseStatus.FAIL.getResponseCode())
                .message(StringUtils.isEmpty(message)?ResponseStatus.FAIL.getDescription() : message)
                .data(data).build();
    }








}
