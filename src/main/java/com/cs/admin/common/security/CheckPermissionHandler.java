package com.cs.admin.common.security;

import cn.hutool.core.util.ObjectUtil;
import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/30 10:37
 */
@Service(value = "ck")
@AllArgsConstructor
public class CheckPermissionHandler {

    private static final String ADMIN_PERMISSION = "admin";

    /**
     * 检查请求用户是否有权限
     *
     * @param permissions 权限列表
     * @return true/false
     */
    public Boolean permit(String... permissions) {

        // 获取当前用户的所有权限
        OnlineInfoDTO infoDTO = WebUtil.getOnlineInfo();
        if (ObjectUtil.isNull(infoDTO)) {
            return Boolean.FALSE;
        }

        // 查询是否又管理员权限
        Set<String> roles = infoDTO.getRoleList().stream().map(SubRoleVO::getRoleCode).collect(Collectors.toSet());
        if (roles.contains(ADMIN_PERMISSION)) {
            return true;
        }

        Set<String> sessionPermissions = infoDTO.getPermissions();
        if (ObjectUtil.isNull(permissions) || sessionPermissions.size() == 0) {
            return Boolean.FALSE;
        }

        return Arrays.stream(permissions).anyMatch(sessionPermissions::contains);
    }
}
