package com.cs.admin.common.controller;

import com.cs.admin.common.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author free loop
 * @since 2019/10/28
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;


    /**
     * 根据请求heard中的token获取用户ID
     *
     * @return 用户ID
     */
    public Long getUserId() {
        return WebUtil.getUserId();
    }


}
