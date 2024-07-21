package com.components.model.enums;

import lombok.Getter;

/**
 * @author dvbn
 * @title: StatusEnum
 * @createDate 2024/5/9 12:07
 */
@Getter
public enum StatusEnum {

    /**
     * 状态枚举
     */
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");
    private final int code;
    private final String label;
    StatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean isCode(int code) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.code == code) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotCode(int code) {
        return !isCode(code);
    }
}
