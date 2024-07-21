package com.components.model.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author dvbn
 * @title: UserPageVO
 * @createDate 2024/5/8 21:50
 */
@Data
public class UserPageVO implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "account")
    private String account;


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
    private String userRole;

    /**
     * 用户状态 1:正常，0:禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
    @Serial
    private static final long serialVersionUID = 1L;
}
