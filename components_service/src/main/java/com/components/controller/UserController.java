package com.components.controller;

import com.components.model.dto.user.UserLoginDTO;
import com.components.model.dto.user.UserPageDTO;
import com.components.model.dto.user.UserSaveDTO;
import com.components.model.result.PageResult;
import com.components.model.result.Result;
import com.components.model.vo.common.TokenVO;
import com.components.model.vo.user.UserPageVO;
import com.components.service.UserService;
import com.components.utils.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dvbn
 * @title: UserController
 * @createDate 2024/5/7 12:14
 */
@RestController
@RequestMapping("/user")
@Tag(name = "后台管理 - 用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        return Result.success("登录成功", token);
    }

    @PostMapping("/save")
    @Operation(summary = "添加用户")
    public Result<Boolean> saveUser(@RequestBody UserSaveDTO userSaveDTO) {
        Boolean res = userService.saveUser(userSaveDTO);
        if (Boolean.FALSE.equals(res)) {
            Result.error(ErrorCode.SYSTEM_ERROR, "添加失败请稍后重试");
        }
        return Result.success("添加成功", res);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<PageResult<UserPageVO>> pageList(@RequestBody UserPageDTO userPageDTO) {

        PageResult<UserPageVO> list = userService.pageList(userPageDTO);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        Boolean res = userService.deleteById(id);
        if (Boolean.TRUE.equals(res)) {
            return Result.success("操作成功", true);
        }
        return Result.error(ErrorCode.SYSTEM_ERROR, "删除失败请稍后重试", false);
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public Result<Boolean> logout(@RequestHeader("Authorization") String token) {
        Boolean res = userService.logout(token);
        if (Boolean.TRUE.equals(res)) {
            return Result.success("操作成功", true);
        }
        return Result.error(ErrorCode.SYSTEM_ERROR, "退出失败请稍后重试", false);
    }

    @Operation(summary = "swagger测试")
    @Parameters({
            @Parameter(name = "id", description = "文件id", in = ParameterIn.PATH),
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)
    })
    @PostMapping("/bodyParamHeaderPath/{id}")
    public String bodyParamHeaderPath(@PathVariable("id") String id, @RequestHeader("token") String token, @RequestParam("name") String name) {
        return "id:" + id + "token:" + token + "name:" + name;
    }
}
