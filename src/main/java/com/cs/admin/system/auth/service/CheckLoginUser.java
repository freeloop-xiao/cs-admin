package com.cs.admin.system.auth.service;

import com.cs.admin.system.manager.domain.dto.AdminDTO;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 15:29
 */
public interface CheckLoginUser {


    /**
     * 检查用户是否存在
     *
     * @param account 账号
     * @param dtoPwd  密码
     * @return adminDTO
     */
    AdminDTO checkUser(String account, String dtoPwd);


    /**
     * 检查密码
     *
     * @param dtoPwd   用户登陆上传密码
     * @param storePwd 保存用户密码
     */
    void checkPassword(String dtoPwd, String storePwd);

}
