package com.cs.admin.common.enums;

import lombok.Getter;

/**
 *
 */

/**
 * @author xiao kun
 * @version 1.0
 * @since 2020/3/11 15:40
 */
@Getter
public enum ResponseCode {

    // 成功返回
    SUCCESS("200", "成功!"),
    // 参数有误或者语义错误
    CLIENT_EXCEPTION("400", "参数有误或者语义错误!"),
    // token无效
    TOKEN_INVALID("401", "令牌无效!"),
    // token过期
    TOKEN_TIME_OUT("402", "token过期!"),
    // 无权限访问
    ACCESS_DENIED("403", "无访问权限!"),
    // 系统异常
    SYSTEM_EXCEPTION("500", "系统异常!");

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应编码
     */
    private final String code;

    /**
     * 响应编码说明
     */
    private final String msg;
}
