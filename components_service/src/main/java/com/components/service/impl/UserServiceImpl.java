package com.components.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.components.mapper.UserMapper;
import com.components.model.domain.LoginUser;
import com.components.model.domain.User;
import com.components.model.dto.user.UserLoginDTO;
import com.components.model.dto.user.UserPageDTO;
import com.components.model.dto.user.UserSaveDTO;
import com.components.model.enums.StatusEnum;
import com.components.model.result.PageResult;
import com.components.model.vo.user.UserPageVO;
import com.components.service.UserService;
import com.components.utils.CheckUtil;
import com.components.utils.ErrorCode;
import com.components.utils.ThrowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.components.constant.RedisConstant.*;

/**
 * @author dvbn
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-05-07 12:09:34
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        //封装Autentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getAccount(), userLoginDTO.getPassword());

        //校验
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //校验失败
        ThrowUtil.throwIf(Objects.isNull(authenticate), ErrorCode.LOGIN_FAIRED, "登录失败");

        //成功
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        User user = principal.getUser();

        // 登录生成token
        Map<String, Object> info = new HashMap<>();
        info.put("userId", user.getId());
        String token = JWTUtil.createToken(info, SYS_SECRET_KEY.getBytes());

        // 将token保存到redis中
        stringRedisTemplate.opsForValue().set(USER_LOGIN_TOKEN_KEY + user.getId(), token, USER_LOGIN_TOKEN_TTL, USER_LOGIN_TOKEN_TIMEUNIT);
        log.info("用户登录成功, token: {}", token);
        return token;
    }

    @Override
    public Boolean saveUser(UserSaveDTO userSaveDTO) {
        // 判断用户是否存在
        String account = userSaveDTO.getAccount();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account);
        long count = count(queryWrapper);
        ThrowUtil.throwIf(count > 0, ErrorCode.OPERATION_ERROR, "用户已存在");

        // 若手机号不为空，则校验手机号是否合法
        String mobile = userSaveDTO.getMobile();
        ThrowUtil.throwIf(StrUtil.isNotBlank(mobile) && CheckUtil.isNotValidMobile(mobile), ErrorCode.PARAMS_ERROR, "手机号不合法");

        // 若邮箱不为空，则校验邮箱是否合法
        String email = userSaveDTO.getEmail();
        ThrowUtil.throwIf(StrUtil.isNotBlank(email) && CheckUtil.isNotValidEmail(email), ErrorCode.PARAMS_ERROR, "邮箱不合法");

        // 生成盐，并加密密码
        String salt = RandomUtil.randomNumbers(5);
        String password = DigestUtil.md5Hex(userSaveDTO.getPassword() + salt);

        // 拷贝对象，添加创建时间
        User user = new User();
        BeanUtil.copyProperties(userSaveDTO, user);
        user.setSalt(salt);
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        return save(user);
    }

    @Override
    public PageResult<UserPageVO> pageList(UserPageDTO userPageDTO) {
        String account = userPageDTO.getAccount();
        Integer status = userPageDTO.getStatus();
        Page<User> page = new Page<>(userPageDTO.getPage(), userPageDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(account), User::getAccount, account);
        queryWrapper.eq(Objects.nonNull(status) && StatusEnum.isCode(status), User::getStatus, status);
        queryWrapper.orderByDesc(User::getUpdateTime);

        Page<User> res = page(page, queryWrapper);
        List<User> records = res.getRecords();
        List<UserPageVO> list = BeanUtil.copyToList(records, UserPageVO.class);
        return new PageResult<>(res.getTotal(), list);
    }

    @Override
    public Boolean deleteById(Long id) {
        User user = getById(id);
        ThrowUtil.throwIf(Objects.isNull(user), ErrorCode.PARAMS_ERROR, "用户不存在");
        return Boolean.TRUE.equals(removeById(id));
    }

    @Override
    public Boolean logout(String token) {
        ThrowUtil.throwIf(StrUtil.isBlank(token), ErrorCode.PARAMS_ERROR, "用户未登录");
        // 删除token
        return stringRedisTemplate.delete(USER_LOGIN_TOKEN_KEY + token);
    }
}




