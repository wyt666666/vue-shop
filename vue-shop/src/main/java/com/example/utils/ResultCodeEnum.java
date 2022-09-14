package com.example.utils;

import lombok.Getter;

/**
 * 数据库是否状态说明
 *
 * @author jtt
 * @date [2022/8/11 10:16]
 */
@Getter
public enum ResultCodeEnum {
    /**
     * amis 登录接口没做200-0的处理 单独返回成功状态码
     */
    AMIS_LOGIN_SUCCESS(0, "登录成功"),
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    FORBIDDEN(403, "Forbidden")
    ;
    private Integer code;
    private String message;
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
