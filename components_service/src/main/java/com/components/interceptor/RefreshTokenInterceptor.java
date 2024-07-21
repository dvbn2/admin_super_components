package com.components.interceptor;

import cn.hutool.core.util.StrUtil;
import com.components.utils.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

import static com.components.constant.RedisConstant.USER_LOGIN_TOKEN_KEY;
import static com.components.constant.RedisConstant.USER_LOGIN_TOKEN_TTL;


/**
 * @author dvbn
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        System.out.println("RefreshTokenInterceptor....");

        //放行登录请求
        String uri = request.getRequestURI();
        if (uri.equals("/user/login")) {
            return true;
        }

        // 1.获取请求头中的token
        String token = request.getHeader("Authorization");

        if (StrUtil.isBlank(token)) {
            return true;
        }
        // 2.基于token获取redis中的用户
        String id = stringRedisTemplate.opsForValue().get(USER_LOGIN_TOKEN_KEY + token);
        // 3.判断用户是否存在
        if (StrUtil.isBlank(id)) {
            return true;
        }
        // 5. 将查询到的id数据保存用户信息到ThreadLocal
        BaseContext.setCurrentId(Long.valueOf(id));

        // 6. 刷新token有效期 放行
        stringRedisTemplate.expire(USER_LOGIN_TOKEN_KEY + token, USER_LOGIN_TOKEN_TTL, TimeUnit.DAYS);

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {

        // 校验完移除用户
        BaseContext.removeCurrentId();
    }
}
