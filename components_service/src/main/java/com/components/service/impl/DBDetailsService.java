package com.components.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.components.model.domain.LoginUser;
import com.components.model.domain.User;
import com.components.service.UserService;
import com.components.utils.ErrorCode;
import com.components.utils.ThrowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DBDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ThrowUtil.throwIf(StringUtils.isEmpty(username), ErrorCode.LOGIN_FAIRED, "用户名为空");

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getAccount, username));

        ThrowUtil.throwIf(Objects.isNull(user), ErrorCode.LOGIN_FAIRED, "用户不存在");

        return new LoginUser(user);
    }
}
