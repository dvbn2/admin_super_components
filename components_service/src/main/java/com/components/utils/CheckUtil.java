package com.components.utils;

import java.util.regex.Pattern;

/**
 * @author dvbn
 * @title: CheckUtils
 * @createDate 2023/12/11 10:15
 */
public class CheckUtil {

    /**
     * 手机号校验
     *
     * @param mobile 待校验的手机号
     * @return true: 是手机号，false: 非手机号
     */
    public static boolean isValidMobile(String mobile) {
        if ((mobile != null) && (!mobile.isEmpty())) {
            return Pattern.matches("^1[3-9]\\d{9}$", mobile);
        }
        return false;
    }

    public static boolean isNotValidMobile(String mobile) {
        return !isValidMobile(mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email 待校验的邮箱
     * @return true: 是邮箱，false: 非邮箱
     */
    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

    public static boolean isNotValidEmail(String email) {
        return !isValidEmail(email);
    }
}



