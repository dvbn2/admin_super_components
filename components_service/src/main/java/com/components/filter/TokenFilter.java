package com.components.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.components.model.domain.LoginUser;
import com.components.model.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RequestFilter;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class TokenFilter extends RequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        //放行登录请求
        if (requestURI.equals("/user/login")) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //获取请求头中的token
        String token = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            //TODO
        }
        //校验令牌
        JWT parseToken = JWTUtil.parseToken(token);
        JWTPayload payload = parseToken.getPayload();
        JSONObject claimsJson = payload.getClaimsJson();
        LoginUser loginUser = claimsJson.get("user", LoginUser.class);
        //把校验完的用户信息重新放入security上下文
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    protected Log getLogger() {
        return null;
    }
}
