package com.components.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 异常日志表
 * @TableName sys_exception
 */
@TableName(value ="exception_info")
@Data
@Builder
public class SysException implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求接口
     */
    private String requestApi;

    /**
     * 请求参数
     */
    private String requestParameter;

    /**
     * 异常类型
     */
    private String exceptionType;

    /**
     * 异常描述
     */
    private String exceptionDescription;

    /**
     * 创建用户id
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}