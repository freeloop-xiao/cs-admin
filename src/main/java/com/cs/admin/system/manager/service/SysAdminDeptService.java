package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.system.manager.domain.entity.SysAdminDept;

/**
 * <p>
 * 用户与部门关联表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-27
 */
public interface SysAdminDeptService extends IService<SysAdminDept> {


    /**
     * 查询部门ID用户数
     *
     * @param deptId 部门ID
     * @return int
     */
    Integer getCountByDeptId(Long deptId);

    /**
     * 用户ID查询部门
    * @param userId 用户ID
     * @return sysAdminDept
     */
    SysAdminDept getByUserId(Long userId);


    /**
     * 删除用户管理部门
     * @param userId 用户ID
     */
    void removeByUserId(Long userId);

}
