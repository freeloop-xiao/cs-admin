package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysRoleConvert;
import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.dto.RolePageDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.entity.SysRole;
import com.cs.admin.system.manager.domain.entity.SysRoleMenu;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import com.cs.admin.system.manager.mapper.SysRoleMapper;
import com.cs.admin.system.manager.service.SysAdminRoleService;
import com.cs.admin.system.manager.service.SysMenuService;
import com.cs.admin.system.manager.service.SysRoleMenuService;
import com.cs.admin.system.manager.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleConvert sysRoleConvert;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysAdminRoleService sysAdminRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(RoleAddDTO addDTO) {

        // 添加角色检查
        checkAdd(addDTO);

        SysRole role = sysRoleConvert.toEntity(addDTO);

        // 保存角色
        save(role);

        // 如果有关联菜单则进行处理
        if (ObjectUtil.isNotNull(addDTO.getMenuIds()) && addDTO.getMenuIds().size() > 0) {
            sysRoleMenuService.saveBatch(addDTO.getMenuIds().stream().distinct().map(x -> new SysRoleMenu(role.getRoleId(), x)).collect(Collectors.toList()));
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long roleId) {

        // 查询是否关联用户
        if (sysAdminRoleService.countByRoleId(roleId) > 0) {
            ReportUtil.throwEx(StrUtil.format("角色ID[{}]已经关联用户,不能删除(如需删除请先解除关联)!", roleId));
        }

        // 删除关联菜单
        sysRoleMenuService.removeByRoleId(roleId);

        // 删除角色
        return removeById(roleId);
    }

    @Override
    public Boolean edit(RoleEditDTO editDTO) {

        checkEdit(editDTO);

        if (ObjectUtil.isNotNull(editDTO.getMenuIds()) && editDTO.getMenuIds().size() > 0) {

            // 已拥有的菜单ID
            Set<Long> menuIds = new HashSet<>(sysRoleMenuService.getMenuIdsByRoleId(editDTO.getRoleId()));

            // 需要删除的菜单ID
            Set<Long> delMenuIds = new HashSet<>(menuIds);
            delMenuIds.removeAll(editDTO.getMenuIds());
            sysRoleMenuService.removeByRoleIdAndMenuIds(editDTO.getRoleId(), delMenuIds);

            // 需新增的菜单ID
            editDTO.getMenuIds().removeAll(menuIds);

            // 检查增加的菜单是否存在
            checkRoleMenus(editDTO.getMenuIds());
            sysRoleMenuService.addByRoleIdAndMenuIds(editDTO.getRoleId(), editDTO.getMenuIds());
        } else {
            sysRoleMenuService.removeByRoleId(editDTO.getRoleId());
        }

        SysRole role = sysRoleConvert.toEntity(editDTO);
        role.setUpdateBy(WebUtil.getUserId());
        return updateById(role);
    }

    @Override
    public RoleVO getVO(Long roleId) {
        RoleVO role = sysRoleConvert.toVO(getById(roleId));

        if (ObjectUtil.isNull(role)) {
            return role;
        }

        role.setMenus(sysMenuService.getMenusByRoleId(roleId));
        return role;
    }

    @Override
    public PageVO<RoleVO> page(RolePageDTO pageDTO) {

        Page<SysRole> pageCondition = PageUtil.page(pageDTO);

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();

        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("role_code", pageDTO.getKey())
                    .or().like("role_name", pageDTO.getKey())
                    .or().like("description", pageDTO.getKey()));
        }

        Page<SysRole> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysRoleConvert.toVO(result.getRecords()));
    }


    @Override
    public List<RoleVO> list(String key) {

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(key)) {
            queryWrapper.or().like("role_code", key);
            queryWrapper.or().like("role_name", key);
            queryWrapper.or().like("description", key);
        }

        return sysRoleConvert.toVO(list(queryWrapper));
    }

    @Override
    public List<SysRole> getByRoleIds(Set<Long> roleIds) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return list(queryWrapper);
    }


    @Override
    public List<RoleVO> getByUserId(Long userId) {
        return sysRoleConvert.toVO(getBaseMapper().selectRolesByUserId(userId));
    }

    @Override
    public List<SubRoleVO> getSubRoleByUserId(Long userId) {
        return sysRoleConvert.toSubRoleVO(getBaseMapper().selectRolesByUserId(userId));
    }

    /**
     * 新增角色检查
     *
     * @param addDTO 增减传输对象
     */
    private void checkAdd(RoleAddDTO addDTO) {

        // 检查角色编码是否存在
        checkRoleCode(addDTO.getRoleCode());

        // 检查角色名称是否存在
        checkRoleName(addDTO.getRoleName());

        // 检查菜单是存在
        checkRoleMenus(addDTO.getMenuIds());
    }

    /**
     * 检查角色Code
     *
     * @param roleCode 角色code
     */
    private void checkRoleCode(String roleCode) {

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", roleCode);

        SysRole sysRole = getOne(queryWrapper);

        if (ObjectUtil.isNotNull(sysRole)) {
            ReportUtil.throwEx(StrUtil.format("角色code[{}]已经存在!", roleCode));
        }
    }

    /**
     * 角色名称
     *
     * @param roleName 角色名称
     */
    private void checkRoleName(String roleName) {

        // 检查角色名称是否存在
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        SysRole sysRole = getOne(queryWrapper);

        if (ObjectUtil.isNotNull(sysRole)) {
            ReportUtil.throwEx(StrUtil.format("角色name[{}]已经存在!", roleName));
        }
    }

    /**
     * 检查菜单列表
     *
     * @param menusSet 菜单列表
     */
    private void checkRoleMenus(Set<Long> menusSet) {

        if (ObjectUtil.isNull(menusSet) || menusSet.size() == 0) {
            return;
        }

        List<SysMenu> menus = sysMenuService.getMenusByIds(new HashSet<>(menusSet));
        Set<Long> menuIdSet = menus.stream().map(SysMenu::getMenuId).collect(Collectors.toSet());

        for (Long menuId : menusSet) {
            if (!menuIdSet.contains(menuId)) {
                ReportUtil.throwEx(StrUtil.format("菜单ID[{}]不存在!", menuId));
            }
        }
    }

    /**
     * 检查角色ID是否存在
     *
     * @param editDTO 角色传输对象
     */
    private void checkEdit(RoleEditDTO editDTO) {

        SysRole sysRole = getById(editDTO.getRoleId());

        if (ObjectUtil.isNull(sysRole)) {
            ReportUtil.throwEx(StrUtil.format("角色ID[{}]不存在!", editDTO.getRoleId()));
        }

        if (!editDTO.getRoleCode().equals(sysRole.getRoleCode())) {
            checkRoleCode(editDTO.getRoleCode());
        }

        if (!editDTO.getRoleName().equals(sysRole.getRoleName())) {
            checkRoleName(editDTO.getRoleName());
        }
    }


}
