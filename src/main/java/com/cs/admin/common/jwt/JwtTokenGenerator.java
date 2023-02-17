package com.cs.admin.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.cs.admin.common.enums.ResponseCode;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * play
 * token生成器
 * payload {
 * Issuer :发行人
 * Subject: 主题
 * Audience: 观众、听众
 * ExpiresAt: 超时时间
 * NotBefore: 不在指定时间前
 * Issued At: 发行时间
 * JWT Id(jti): Token Id
 * }
 *
 * @author free loop
 * @version 1.0
 * @since 2020/12/28 22:20
 */
@Slf4j
@Component
public class JwtTokenGenerator {

    @Resource
    private Algorithm algorithm;


    /**
     * 生成token
     *
     * @param userId 用户ID
     * @param jwtId  jwtId
     * @param roles  角色列表
     * @return token
     */
    public String generate(Long userId, String jwtId, List<String> roles) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer(TokenUtil.DEFAULT_ISSUER)
                    .withSubject(TokenUtil.DEFAULT_SUBJECT)
                    .withAudience(TokenUtil.DEFAULT_AUDIENCE)
                    .withIssuedAt(new Date())
                    .withJWTId(jwtId)
                    .withClaim(TokenUtil.TYPE, TokenUtil.ACCESS_TYPE)
                    .withClaim(TokenUtil.USER_ID, userId)
                    .withClaim(TokenUtil.ROLES, roles)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("生成token异常! [" + exception.getMessage() + "]");
            ReportUtil.throwEx("生成token异常![" + exception.getMessage() + "]");
        }
        return token;
    }


    /**
     * 验证token
     *
     * @param token token
     * @return decodedJWT
     */
    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verification = JWT.require(algorithm)
                    .withIssuer(TokenUtil.DEFAULT_ISSUER)
                    .withSubject(TokenUtil.DEFAULT_SUBJECT)
                    .withAudience(TokenUtil.DEFAULT_AUDIENCE)
                    .withClaim(TokenUtil.TYPE, TokenUtil.ACCESS_TYPE)
                    .withClaimPresence(TokenUtil.ROLES)
                    .build();
            return verification.verify(token);
        } catch (JWTVerificationException | IllegalArgumentException expiredException) {
            ReportUtil.throwEx(HttpStatus.UNAUTHORIZED.value(), ResponseCode.TOKEN_INVALID.getMsg());
        }
        return null;
    }

}
