package com.njj.blog.common.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * 响应结果实体
 *
 * @author 倪佳俊
 * @date 2023/2/18
 */
@Data
@Builder
public class ResponseResult<T> {
    @Tolerate
    public ResponseResult(){

    }
    /**
     * 响应时间戳
     */
    private long timestamp;
    /**
     * 响应状态
     */
    private String status;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;
}
