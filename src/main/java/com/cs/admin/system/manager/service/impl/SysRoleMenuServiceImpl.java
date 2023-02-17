package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.system.manager.domain.entity.SysRoleMenu;
import com.cs.admin.system.manager.mapper.SysRoleMenuMapper;
import com.cs.admin.system.manager.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public Integer countByMenuId(Long menuId) {
        return getBaseMapper().countByMenuId(menuId);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        remove(queryWrapper);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = list(queryWrapper);
        return roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }


    @Override
    public void addByRoleIdAndMenuIds(Long roleId, Set<Long> menuIds) {

        if (ObjectUtil.isNull(menuIds) || menuIds.size() == 0) {
            return;
        }
        List<SysRoleMenu> list = menuIds.stream().distinct().map(x -> new SysRoleMenu(roleId, x)).collect(Collectors.toList());
        saveBatch(list);
    }

    @Override
    public void removeByRoleIdAndMenuIds(Long roleId, Set<Long> menuIds) {

        if (ObjectUtil.isNull(menuIds) || menuIds.size() == 0) {
            return;
        }

        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        queryWrapper.in("menu_id", menuIds);

        remove(queryWrapper);
    }
}
