package com.components.constant;

import java.util.concurrent.TimeUnit;

/**
 * @author dvbn
 * @title: RedisConstant
 * @createDate 2024/5/8 13:13
 */
public interface RedisConstant {

    String USER_LOGIN_TOKEN_KEY = "user:login:token:";
    Long USER_LOGIN_TOKEN_TTL = 12L;
    //TOKEN密钥
    String SYS_SECRET_KEY = "TokenConstant";
    //时间单位
    TimeUnit USER_LOGIN_TOKEN_TIMEUNIT= TimeUnit.HOURS;
}
