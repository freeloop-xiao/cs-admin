package com.cs.admin.common.enums;

import lombok.Getter;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/14 10:12
 */
@Getter
public enum PermissionLevel {

    // 自定义权限
    CUSTOM(0, "自定义权限"),
    // 已发布权限
    RELEASE(1, "已发布权限"),
    // 未发布权限
    NOT_RELEASE(2, "未发布权限");

    /**
     * 级别
     */
    private final int level;

    /**
     * 说明
     */
    private final String desc;

    PermissionLevel(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }
}
