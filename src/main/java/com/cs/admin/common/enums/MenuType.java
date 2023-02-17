package com.cs.admin.common.enums;

import lombok.Getter;


/**
 * @author xiao kun
 * @version 1.0
 * @since 2020/3/11 15:40
 */
@Getter
public enum MenuType {

    // M目录
    DIR("M", "目录"),
    // C菜单
    MENU("C", "菜单"),
    // F按钮
    BUTTON("F", "按钮");

    MenuType(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    /**
     * 响应编码
     */
    private final String type;

    /**
     * 响应编码说明
     */
    private final String msg;
}
