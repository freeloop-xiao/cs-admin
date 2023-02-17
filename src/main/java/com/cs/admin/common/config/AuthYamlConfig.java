package com.cs.admin.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 登录配置信息
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 11:47
 */
@Data
@Component
public class AuthYamlConfig {


    /**
     * 用户信息缓存前缀
     */
    public static final String TOKEN_USER_INFO_PREFIX = "token:info:";

    /**
     * 请求头basic前缀
     */
    public static final String HEADER_BASIC = "Basic";


    @Value("${token.client-id}")
    private String clientId;

    @Value("${token.client-secret}")
    private String clientSecret;

    @Value("${token.private-secret}")
    private String priKey;

    @Value("${token.public-secret}")
    private String pubKey;

    @Value("${token.expire}")
    private Long expire;

    @Value("${token.renew}")
    private Long renew;

    @Value("${token.detect}")
    private Long detect;

}
