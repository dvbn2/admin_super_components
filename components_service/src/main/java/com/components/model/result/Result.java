package com.components.model.result;

import com.components.utils.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @author dvbn
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 编码：1成功，0和其它数字为失败
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> success(String msg, T object) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.data = object;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode, String msg, T object) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = errorCode.getCode();
        result.data = object;
        return result;
    }


    public static <T> Result<T> error(ErrorCode errorCode, String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = errorCode.getCode();
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        Result<T> result = new Result<>();
        result.msg = errorCode.getMessage();
        result.code = errorCode.getCode();
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 50000;
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = code;
        return result;
    }

}
