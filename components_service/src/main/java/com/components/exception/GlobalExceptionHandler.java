package com.components.exception;

import com.components.model.result.Result;
import com.components.utils.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author dvbn
 * @title: GlobalExceptionHandler
 * @createDate 2023/9/16 20:56
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 可预知异常
     *
     * @param e 可预知异常对象
     * @return 异常信息
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 不可预知异常
     *
     * @param e 不可预知异常对象
     * @return 异常信息
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return Result.error(ErrorCode.SYSTEM_ERROR.getMessage());
    }
}
