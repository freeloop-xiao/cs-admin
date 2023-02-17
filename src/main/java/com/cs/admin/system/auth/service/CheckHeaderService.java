package com.cs.admin.system.auth.service;

/**
 * <p>
 * 验证请求头
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/30 12:02
 */
public interface CheckHeaderService {

    /**
     * 验证请求头Basic
     * @param authentication 请求头
     */
    void headerAuthentication(String authentication);

}
