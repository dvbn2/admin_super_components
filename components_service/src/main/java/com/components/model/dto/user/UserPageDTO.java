package com.components.model.dto.user;

import com.components.model.result.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author dvbn
 * @title: UserPageDTO
 * @createDate 2024/5/8 21:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDTO extends PageParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名", example = "admin")
    private String account;

    @Schema(description = "状态值", example = "1")
    private Integer status;
}
