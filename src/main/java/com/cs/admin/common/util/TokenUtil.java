package com.cs.admin.common.util;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * token生成工具类
 *
 * @author free loop
 * @version 1.0
 * @since 2020/12/28 17:11
 */
public class TokenUtil {


    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = SecureUtil.md5("111111");

    /**
     * 雪花算法生成id
     */
    public static final Snowflake SNOW_FLAKE = IdUtil.getSnowflake(1L, 1L);


    /************************   token内部字段  ****************************************/

    /**
     * 用户id
     */
    public static final String USER_ID = "user_id";


    /**
     * 角色字段
     */
    public static final String ROLES = "roles";

    /**
     * token类型
     */
    public static final String TYPE = "type";


    /************************   token默认字段默认内容  ****************************************/

    /**
     * 系统默认发证机构
     */
    public static final String DEFAULT_ISSUER = "cs-web";

    /**
     * 默认主题
     */
    public static final String DEFAULT_SUBJECT = "topic";

    /**
     * 默认受众
     */
    public static final String DEFAULT_AUDIENCE = "admin";

    /************************   token类型  ****************************************/

    /**
     * 访问token
     */
    public static final String ACCESS_TYPE = "accessToken";


    /**
     * 请求头前缀
     */
    public static final String BEARER = "Bearer ";


    /**
     * Basic请求头前缀
     */
    public static final String BASIC = "Basic ";


}
