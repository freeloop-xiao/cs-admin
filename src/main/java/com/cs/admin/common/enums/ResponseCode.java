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

    SUCCESS(200, "操作成功!"),
    CLIENT_EXCEPTION(400, "参数有误或者语义错误!"),
    TOKEN_INVALID(401, "令牌无效!"),
    TOKEN_TIME_OUT(402, "token过期!"),
    ACCESS_DENIED(403, "无访问权限!"),
    SYSTEM_EXCEPTION(500, "系统异常!");

    ResponseCode(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 响应编码
     */
    private final Integer status;

    /**
     * 响应编码说明
     */
    private final String msg;
}
