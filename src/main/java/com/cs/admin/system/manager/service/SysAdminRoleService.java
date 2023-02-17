package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.system.manager.domain.entity.SysAdminRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysAdminRoleService extends IService<SysAdminRole> {

    /**
     * 角色ID统计用户数
     *
     * @param roleId 角色ID
     * @return int
     */
    Integer countByRoleId(Long roleId);

    /**
     * 删除用户角色ID
     *
     * @param userId 用户ID
     */
    void removeByUserId(Long userId);

    /**
     * 查询用户管理角色ID
     *
     * @param userId 用户ID
     * @return list
     */
    List<SysAdminRole> getByUserId(Long userId);

    /**
     * 删除用户角色关联
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     */
    void removeAdminRoles(Long userId, Set<Long> roleIds);

}
