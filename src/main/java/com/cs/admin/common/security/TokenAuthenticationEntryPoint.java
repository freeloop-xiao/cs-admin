package com.cs.admin.common.security;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.cs.admin.common.enums.ResponseCode;
import com.cs.admin.common.util.ResponseUtil;
import com.cs.admin.common.vo.ErrorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证拦截器
 *
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 16:16
 */
@Slf4j
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error(ExceptionUtil.stacktraceToString(authException));
        ResponseUtil.writeJsonData(response, ErrorVO.error(HttpStatus.UNAUTHORIZED.value(), ResponseCode.TOKEN_INVALID.getMsg()));
    }

}
