package com.components.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author dvbn
 * @title: UserLoginDTO
 * @createDate 2024/5/7 13:48
 */
@Data
public class UserLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "account", example = "admin", required = true)
    @NotBlank
    private String account;

    @Schema(name = "password", example = "123456", required = true)
    @NotBlank
    private String password;
}
