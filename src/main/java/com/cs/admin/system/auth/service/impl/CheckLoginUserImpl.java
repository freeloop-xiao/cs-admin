package com.cs.admin.system.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.cs.admin.system.auth.service.CheckLoginUser;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.service.SysAdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 15:54
 */
@Service
@AllArgsConstructor
public class CheckLoginUserImpl implements CheckLoginUser {

    private final PasswordEncoder passwordEncoder;

    private final SysAdminService sysAdminService;


    @Override
    public AdminDTO checkUser(String account, String dtoPwd) {

        AdminDTO adminDTO = sysAdminService.getAdminDtoByAccount(account);

        if (ObjectUtil.isNull(adminDTO)) {
            ReportUtil.throwEx(StrUtil.format("账户[{}]不存在!", account));
        }

        if (adminDTO.getIsLocked()) {
            ReportUtil.throwEx(StrUtil.format("账户[{}]已锁定,请联系管理员解锁!", account));
        }

        // 检查密码
        checkPassword(dtoPwd, adminDTO.getPassword());

        return adminDTO;
    }

    @Override
    public void checkPassword(String dtoPwd, String storePwd) {

        dtoPwd = SecureUtil.md5(dtoPwd);

        if (!passwordEncoder.matches(dtoPwd, storePwd)) {
            ReportUtil.throwEx("密码错误!");
        }
    }
}
