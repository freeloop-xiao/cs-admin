package com.cs.admin.system.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cs.admin.common.jwt.JwtTokenGenerator;
import com.cs.admin.common.util.TokenUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.system.auth.domain.dto.AuthDTO;
import com.cs.admin.system.auth.domain.vo.AuthVO;
import com.cs.admin.system.auth.service.AuthService;
import com.cs.admin.system.auth.service.CaptchaService;
import com.cs.admin.system.auth.service.CheckLoginUser;
import com.cs.admin.system.auth.service.OnlineService;
import com.cs.admin.system.manager.domain.covert.SysAdminConvert;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import com.cs.admin.system.manager.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 9:25
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CaptchaService captchaService;

    private final CheckLoginUser checkLoginUser;

    private final SysAdminConvert sysAdminConvert;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final OnlineService onlineService;

    private final SysPermissionService sysPermissionService;


    @Override
    public AuthVO login(AuthDTO dto) {

        // 验证图形验证码
        captchaService.verify(dto.getSerNo(), dto.getCode());

        // 查询用户是否存在、检查用户是否锁定、密码校验
        AdminDTO admin = checkLoginUser.checkUser(dto.getAccount(), dto.getPassword());

        // 类型转换
        AdminVO adminVO = sysAdminConvert.toVO(admin);

        // 获取角色列表
        List<String> roles = adminVO.getRoleList().stream().map(SubRoleVO::getRoleCode).collect(Collectors.toList());
        String jwtId = TokenUtil.SNOW_FLAKE.nextIdStr();
        String token = jwtTokenGenerator.generate(admin.getUserId(), jwtId, roles);

        // 获取权限标识code
        Set<Long> permissionIds = admin.getPermissions().stream()
                .filter(StrUtil::isNotEmpty)
                .flatMap(x -> Arrays.stream(x.split(",")))
                .filter(StrUtil::isNotEmpty)
                .map(Long::valueOf)
                .collect(Collectors.toSet());

        // 获取权限code
        Set<String> permissions = sysPermissionService.list(permissionIds).stream()
                .map(SysPermission::getCode)
                .filter(StrUtil::isNotEmpty)
                .collect(Collectors.toSet());

        // 构建保存登陆用户信息
        onlineService.buildOnlineAndSave(jwtId, permissions, adminVO);

        return new AuthVO(adminVO, token);
    }

    @Override
    public void logout() {
        onlineService.remove(WebUtil.getOnlineInfo());
    }

}
