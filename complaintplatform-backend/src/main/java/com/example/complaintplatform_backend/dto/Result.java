package com.example.complaintplatform_backend.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 统一API响应结果封装类
 * @param <T> 数据类型
 */
@Data
public class Result<T> implements Serializable {
    private Integer code; // 状态码
    private String message; // 返回消息
    private T data; // 返回数据
    private Date timestamp; // 时间戳

    public Result() {
        this.timestamp = new Date();
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(String message) {
        return error(500, message);
    }
}
