package com.components.model.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dvbn
 * @title: UserSaveDTO
 * @createDate 2024/5/8 15:41
 */
@Data
public class UserSaveDTO implements Serializable {

    /**
     * 用户名
     */
    @TableField(value = "account")
    private String account;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 电子邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别 2:女，1:男
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 爱好集合
     */
    @TableField(value = "hobby_list")
    private String hobbyList;


    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;


    /**
     * 用户角色：admin/superadmin
     */
    @TableField(value = "user_role")
    @Schema(description = "用户角色：admin/superadmin", example = "admin")
    private String userRole;


    /**
     * 用户状态 1:正常，0:禁用
     */
    @TableField(value = "status")
    @Schema(description = "用户状态 1:正常，0:禁用", example = "1")
    private Integer status;
}
