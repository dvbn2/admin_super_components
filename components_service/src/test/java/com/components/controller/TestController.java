package com.components.controller;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.components.model.domain.User;
import com.components.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * @author dvbn
 * @title: TestController
 * @createDate 2024/5/8 15:07
 */
@SpringBootTest
public class TestController {

    @Resource
    private UserService userService;
    @Test
    public void saveUser(){
        User user = new User();
        user.setAccount("admin");
        String originalPassword = "123456";
        String salt = RandomUtil.randomNumbers(5);
        String password = DigestUtil.md5Hex(originalPassword + salt);
        user.setPassword(password);
        user.setSalt(salt);
        boolean res = userService.save(user);
        System.out.println(res);
    }
}
