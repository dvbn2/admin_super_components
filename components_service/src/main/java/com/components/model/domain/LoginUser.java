package com.components.model.domain;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {

    //用户信息
    private User user;
    //权限
    private List<String> list;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    public LoginUser(User user, List<String> list) {
        this.user = user;
        this.list = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (!ObjectUtil.isEmpty(simpleGrantedAuthorities)) {
            return simpleGrantedAuthorities;
        }
        list.forEach(item -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(item);
            simpleGrantedAuthorities.add(simpleGrantedAuthority);
        });
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
