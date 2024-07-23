package com.components.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "sys_user")
@Data
public class User implements Serializable {
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
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 微信平台开放id
     */
    @TableField(value = "union_id")
    private String unionId;

    /**
     * 用户角色：admin/superadmin
     */
    @TableField(value = "user_role")
    private String userRole;

    /**
     * 公众号openId
     */
    @TableField(value = "mp_open_id")
    private String mpOpenId;

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

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}