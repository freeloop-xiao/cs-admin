package com.cs.admin.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 验证码yaml配置信息
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 11:47
 */
@Data
@Component
public class CaptchaYamlConfig {

    @Value("${captcha.expire}")
    private Long expire;

}
