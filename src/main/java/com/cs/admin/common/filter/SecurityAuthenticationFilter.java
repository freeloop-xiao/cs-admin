package com.cs.admin.common.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.Header;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cs.admin.system.auth.service.OnlineService;
import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.enums.ResponseCode;
import com.cs.admin.common.jwt.JwtTokenGenerator;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.TokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 鉴权过滤器
 *
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 13:50
 */
@AllArgsConstructor
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenGenerator jwtTokenGenerator;

    private final OnlineService onlineService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 获取认证请求头
        String authorization = request.getHeader(Header.AUTHORIZATION.getValue());

        if (StringUtils.hasText(authorization) && authorization.startsWith(TokenUtil.BEARER)) {

            // 截取请求头
            String token = authorization.substring(TokenUtil.BEARER.length());

            // 验证token
            DecodedJWT jwt = jwtTokenGenerator.verify(token);

            // 设置登录认证信息
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwt));
        }
        // 过滤器
        chain.doFilter(request, response);
    }


    /**
     * 从redis获取用户在线信息
     *
     * @param jwt jwtId
     * @return usernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(DecodedJWT jwt) {

        // 获取在线信息
        OnlineInfoDTO onlineInfo = onlineService.getOnlineInfo(jwt);

        // 如果redis中无在线用户信息
        if (ObjectUtil.isNull(onlineInfo)
                || !jwt.getId().equals(onlineInfo.getJwtId())) {
            ReportUtil.throwEx(HttpStatus.UNAUTHORIZED.value(), ResponseCode.TOKEN_INVALID.getMsg());
        }
        // 续约检查
        onlineService.renew(onlineInfo);
        return new UsernamePasswordAuthenticationToken(onlineInfo.getUserId(), onlineInfo, new ArrayList<>());
    }

}
