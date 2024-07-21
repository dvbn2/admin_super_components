package com.components.aspect;

import cn.hutool.json.JSONUtil;
import com.components.model.domain.SysException;
import com.components.service.SysExceptionService;

import com.components.utils.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dvbn
 * @title: ExceptionAspect
 * @createDate 2024/1/20 21:08
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @Resource
    private SysExceptionService exceptionService;


    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("execution(* com.components.controller.*.*(..))")
    public void doBefore() {
        // 接收到请求，记录请求开始时间
        startTime.set(System.currentTimeMillis());
    }

    @After("execution(* com.components.controller.*.*(..))")
    public void doAfter() {
        // 移除请求时间
        System.out.println(startTime.get());
        startTime.remove();
    }


    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint joinPoint
     * @param e         e
     */
    @AfterThrowing(pointcut = "execution(* com.components.controller.*.*(..))", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        }

        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            assert request != null;
            exceptionService.save(
                    SysException.builder()
                            // 请求参数
                            .requestParameter(JSONUtil.toJsonStr(converMap(request.getParameterMap())))
                            // 请求方法名
                            .requestMethod(className + "." + method.getName())
                            // 异常名称
                            .exceptionType(e.getClass().getName())
                            // 异常信息
                            .exceptionDescription(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()))
                            // 执行人ID
                            .createUser(BaseContext.getCurrentId())
                            // 操作URI
                            .requestApi(request.getRequestURI())
                            // 请求ip
                            .ipAddress(request.getRemoteHost())
                            // 发生异常时间
                            .createTime(LocalDateTime.now())
                            .build()
            );
        } catch (Exception e2) {
            log.error("异常记录异常 : ", e2);
        }
    }

    /**
     * 请求参数
     *
     * @param paramMap 请求参数map集合
     * @return 参数拼接后的map集合
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<>(16);
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常消息
     * @param elements         异常信息
     * @return 完整的异常信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stet : elements) {
            stringBuilder.append(stet).append("<br/>");
        }
        return exceptionName + ":" + exceptionMessage + "<br/>" + stringBuilder.toString();
    }
}
