package com.cs.admin.common.security;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.cs.admin.common.util.ResponseUtil;
import com.cs.admin.common.vo.ErrorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 16:16
 */
@Slf4j
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.error(ExceptionUtil.stacktraceToString(e));
        ResponseUtil.writeJsonData(httpServletResponse, ErrorVO.error(HttpStatus.FORBIDDEN.value(), "无访问权限!"));
    }
}
