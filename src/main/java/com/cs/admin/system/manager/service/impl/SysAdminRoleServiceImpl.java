package com.cs.admin.system.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.system.manager.domain.entity.SysAdminRole;
import com.cs.admin.system.manager.mapper.SysAdminRoleMapper;
import com.cs.admin.system.manager.service.SysAdminRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysAdminRoleServiceImpl extends ServiceImpl<SysAdminRoleMapper, SysAdminRole> implements SysAdminRoleService {


    @Override
    public Integer countByRoleId(Long roleId) {
        return getBaseMapper().countByRoleId(roleId);
    }


    @Override
    public void removeByUserId(Long userId) {
        getBaseMapper().deleteByUserId(userId);
    }

    @Override
    public List<SysAdminRole> getByUserId(Long userId) {

        QueryWrapper<SysAdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return list(queryWrapper);
    }

    @Override
    public void removeAdminRoles(Long userId, Set<Long> roleIds) {

        QueryWrapper<SysAdminRole> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", userId);
        queryWrapper.in("role_id", roleIds);

        remove(queryWrapper);
    }
}
