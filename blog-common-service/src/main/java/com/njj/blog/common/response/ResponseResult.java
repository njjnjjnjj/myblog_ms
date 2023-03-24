package com.njj.blog.common.response;

import lombok.Builder;
import lombok.Data;

/**
 * 响应结果实体
 *
 * @author 倪佳俊
 * @date 2023/2/18
 */
@Data
@Builder
public class ResponseResult<T> {

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

    /*private ResponseResult(){
    }


    public static class Builder<T>{
        private long timestamp;
        private String status;
        private String message;
        private T data;

        public Builder<T> timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder<T> status(String status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseResult<T> build(){
            ResponseResult<T> responseResult = new ResponseResult<T>();
            responseResult.timestamp = this.timestamp;
            responseResult.status = this.status;
            responseResult.message = this.message;
            responseResult.data = this.data;
            return responseResult;
        }
    }*/
}
