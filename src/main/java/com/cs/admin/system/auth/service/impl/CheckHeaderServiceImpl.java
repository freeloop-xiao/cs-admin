package com.cs.admin.system.auth.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import com.cs.admin.system.auth.service.CheckHeaderService;
import com.cs.admin.common.config.AuthYamlConfig;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.TokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/30 12:05
 */
@Service
@AllArgsConstructor
public class CheckHeaderServiceImpl implements CheckHeaderService {

    private final AuthYamlConfig authYamlConfig;

    @Override
    public void headerAuthentication(String authentication) {

        // 验证请求头是否合法
        if (ObjectUtil.isNull(authentication)
                || authentication.length() < TokenUtil.BASIC.length()) {
            ReportUtil.throwEx(HttpStatus.UNAUTHORIZED.value(), "Basic无效!");
        }

        // 验证请求头合法性,以及正确性
        String body = authentication.substring(TokenUtil.BASIC.length());
        body = Base64.decodeStr(body);
        String[] client = body.split(":");

        if (!authYamlConfig.getClientId().equals(client[0])
                || !authYamlConfig.getClientSecret().equals(client[1])) {
            ReportUtil.throwEx(HttpStatus.UNAUTHORIZED.value(), "Basic无效!");
        }

    }
}
