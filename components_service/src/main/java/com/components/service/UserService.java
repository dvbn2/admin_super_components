package com.components.service;

import cn.hutool.core.date.chinese.SolarTerms;
import com.baomidou.mybatisplus.extension.service.IService;
import com.components.model.domain.User;
import com.components.model.dto.user.UserLoginDTO;
import com.components.model.dto.user.UserPageDTO;
import com.components.model.dto.user.UserSaveDTO;
import com.components.model.result.PageResult;
import com.components.model.vo.common.TokenVO;
import com.components.model.vo.user.UserPageVO;

import java.util.List;

/**
* @author dvbn
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-05-07 12:09:34
*/
public interface UserService extends IService<User> {

    String login(UserLoginDTO userLoginDTO);

    Boolean saveUser(UserSaveDTO userSaveDTO);

    PageResult<UserPageVO> pageList(UserPageDTO userPageDTO);

    Boolean deleteById(Long id);

    Boolean logout(String token);
}
