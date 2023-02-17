package com.cs.admin.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.cs.admin.common.dto.OnlineInfoDTO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * 获取当前登录用户信息工具类
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2020/12/31 15:24
 */
public class WebUtil {


    /**
     * 获取当前登录用户userId
     *
     * @return userId
     */
    public static Long getUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    /**
     * 获取当前登录用户信息
     *
     * @return OnlineInfoDTO
     */
    public static OnlineInfoDTO getOnlineInfo() {
        if (ObjectUtil.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            return null;
        }
        return (OnlineInfoDTO) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }


    /**
     * 清理Authentication
     */
    public static void removeOnlineInfo() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }


}
