package com.cs.admin.system.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cs.admin.system.auth.domain.vo.ImageVO;
import com.cs.admin.system.auth.service.CaptchaService;
import com.cs.admin.common.config.CaptchaConfig;
import com.cs.admin.common.enums.CaptchaEnum;
import com.cs.admin.common.exception.CoreException;
import com.cs.admin.common.util.ReportUtil;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 22:38
 */
@Slf4j
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private static final String DOT = ".";

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public ImageVO captcha() {

        // 获取运算的结果
        Captcha captcha = selectCaptcha(CaptchaEnum.arithmetic, CaptchaConfig.INSTANCE);

        String serNo = IdUtil.simpleUUID();

        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();

        if (captcha.getCharType() - 1 == CaptchaEnum.arithmetic.ordinal() && captchaValue.contains(DOT)) {
            captchaValue = captchaValue.split("\\.")[0];
        }

        stringRedisTemplate.opsForValue().set(CaptchaConfig.CAPTCHA_CODE_KEY + serNo, captchaValue, CaptchaConfig.INSTANCE.getExpiration(), TimeUnit.MINUTES);
        ImageVO imageVO = new ImageVO(serNo, captcha.toBase64());
        imageVO.setCode(captchaValue);
        return imageVO;
    }


    @Override
    public void verify(String serNo, String value) {

        String key = CaptchaConfig.CAPTCHA_CODE_KEY + serNo;

        String storeValue = stringRedisTemplate.opsForValue().get(key);

        stringRedisTemplate.delete(key);

        if (StrUtil.isEmpty(storeValue)) {
            ReportUtil.throwEx("验证码无效!");
        }

        if (!value.equals(storeValue)) {
            ReportUtil.throwEx("验证码错误!");
        }
    }

    /**
     * 获取验证码格式
     *
     * @param captchaEnum   验证码类型
     * @param captchaConfig 验证码配置
     * @return captcha
     */
    private Captcha selectCaptcha(CaptchaEnum captchaEnum, CaptchaConfig captchaConfig) {
        Captcha captcha;
        synchronized (this) {
            switch (captchaEnum) {
                case arithmetic:
                    // 算术类型 https://gitee.com/whvse/EasyCaptcha
                    captcha = new ArithmeticCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    // 几位数运算，默认是两位
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case chinese:
                    captcha = new ChineseCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case chinese_gif:
                    captcha = new ChineseGifCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case gif:
                    captcha = new GifCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                case spec:
                    captcha = new SpecCaptcha(captchaConfig.getWidth(), captchaConfig.getHeight());
                    captcha.setLen(captchaConfig.getLength());
                    break;
                default:
                    throw new CoreException("验证码配置信息错误！正确配置查看 CaptchaEnum ");
            }
        }

        if (StrUtil.isNotBlank(captchaConfig.getFontName())) {
            captcha.setFont(new Font(captchaConfig.getFontName(), Font.PLAIN, captchaConfig.getFontSize()));
        }
        return captcha;
    }
}
