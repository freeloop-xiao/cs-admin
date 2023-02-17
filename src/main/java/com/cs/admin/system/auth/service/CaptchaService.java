package com.cs.admin.system.auth.service;

import com.cs.admin.system.auth.domain.vo.ImageVO;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 21:53
 */
public interface CaptchaService {

    /**
     * 生成图形验证码
     *
     * @return imageVO
     */
    ImageVO captcha();


    /**
     * 验证验证码
     *
     * @param serNo 流水
     * @param value 验证码值
     */
    void verify(String serNo, String value);

}
