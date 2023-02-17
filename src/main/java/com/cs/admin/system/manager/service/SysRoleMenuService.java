package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.system.manager.domain.entity.SysRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 通过菜单ID统计授权角色个数
     *
     * @param menuId 菜单ID
     * @return int
     */
    Integer countByMenuId(Long menuId);


    /**
     * 删除角色关联菜单
     *
     * @param roleId 角色ID
     */
    void removeByRoleId(Long roleId);


    /**
     * 通过角色获取菜单ID列表
     * @param roleId 角色ID
     * @return list
     */
    List<Long> getMenuIdsByRoleId(Long roleId);


    /**
     * 角色新增菜单
     * @param roleId 角色ID
     * @param menuIds 菜单ID
     */
    void addByRoleIdAndMenuIds(Long roleId, Set<Long> menuIds);

    /**
     * 删除角色菜单列表
     * @param roleId 角色ID
     * @param menuIds 菜单ID列表
     */
    void removeByRoleIdAndMenuIds(Long roleId, Set<Long> menuIds);
}
