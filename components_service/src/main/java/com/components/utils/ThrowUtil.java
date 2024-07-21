package com.components.utils;


import com.components.exception.BusinessException;

/**
 * 抛异常工具类
 */
public class ThrowUtil {

    /**
     * 条件成立则抛异常
     *
     * @param condition 抛出异常的条件
     * @param runtimeException 抛出的异常对象
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 抛出异常的条件
     * @param errorCode 错误码枚举
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 抛出异常的条件
     * @param errorCode 错误码枚举
     * @param message 自定义错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
