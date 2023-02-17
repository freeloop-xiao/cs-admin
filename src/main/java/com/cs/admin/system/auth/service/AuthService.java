package com.cs.admin.system.auth.service;

import com.cs.admin.system.auth.domain.dto.AuthDTO;
import com.cs.admin.system.auth.domain.vo.AuthVO;

/**
 * <p>
 * 认证登陆业务实现
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 21:53
 */
public interface AuthService {

    /**
     * 登录认证接口
     *
     * @param dto 登录认证传输对象
     * @return authVO
     */
    AuthVO login(AuthDTO dto);

    /**
     * 退出登录
     */
    void logout();


}
