package com.cs.admin.common.jwt;

import lombok.Data;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * token生成传输对象
 * <p>
 * iss (issuer)：签发人
 * <p>
 * exp (expiration time)：过期时间
 * <p>
 * sub (subject)：主题(JWT所面向的用户)
 * <p>
 * aud (audience)：受众(接收JWT的一方)
 * <p>
 * nbf (Not Before)：生效时间
 * <p>
 * iat (Issued At)：签发时间
 * <p>
 * jti (JWT ID)：编号
 *
 * @author free loop
 * @version 1.0
 * @since 2020/12/28 22:51
 */
@Data
public class TokenGeneratorDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账户
     */
    private String account;

    /**
     * 用户角色集合
     */
    private Set<String> roles;

    /**
     * token类型  accessToken
     */
    private String type;


    /**
     * 编号
     */
    private String jwtId;

    /**
     * 过期时间
     */
    private Date expAt;

    /**
     * 发行时间
     */
    private Date issuedAt;

    /**
     * 在这个时间点之前是无效
     */
    private Date notBefore;

    /**
     * 扩展参数
     */
    private Map<String, Object> extParam;

    public TokenGeneratorDTO(Long userId, String account, String jwtId, Set<String> roles) {
        this.userId = userId;
        this.account = account;
        this.jwtId = jwtId;
        this.roles = roles;
    }

}
