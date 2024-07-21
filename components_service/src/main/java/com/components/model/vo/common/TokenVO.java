package com.components.model.vo.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author dvbn
 * @title: TokenVO
 * @createDate 2024/5/7 13:50
 */
@Data
@Builder
public class TokenVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String token;
    private Long userId;
}
