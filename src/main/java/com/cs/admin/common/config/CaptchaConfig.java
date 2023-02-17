package com.cs.admin.common.config;

import lombok.Data;

/**
 * <p>
 * 验证码配置(参考el admin项目验证码获取实现)
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/2 18:09
 */
@Data
public class CaptchaConfig {


    /**
     * 图形验证码redis key前缀
     */
    public static final String CAPTCHA_CODE_KEY = "captcha:";

    /**
     * 单例
     */
    public static final CaptchaConfig INSTANCE = new CaptchaConfig();

    /**
     * 验证码有效期 分钟
     */
    private Long expiration = 2L;
    /**
     * 验证码内容长度
     */
    private int length = 2;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
    /**
     * 验证码字体
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize = 25;

}
