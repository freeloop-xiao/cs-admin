package com.cs.admin.system.manager.service.impl;

import com.cs.admin.system.manager.domain.entity.SysAdminDept;
import com.cs.admin.system.manager.mapper.SysAdminDeptMapper;
import com.cs.admin.system.manager.service.SysAdminDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与部门关联表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-27
 */
@Service
public class SysAdminDeptServiceImpl extends ServiceImpl<SysAdminDeptMapper, SysAdminDept> implements SysAdminDeptService {

    @Override
    public Integer getCountByDeptId(Long deptId) {
        return getBaseMapper().countByDeptId(deptId);
    }


    @Override
    public SysAdminDept getByUserId(Long userId) {
        return getBaseMapper().selectByUserId(userId);
    }

    @Override
    public void removeByUserId(Long userId) {
        getBaseMapper().deleteByUserId(userId);
    }
}
