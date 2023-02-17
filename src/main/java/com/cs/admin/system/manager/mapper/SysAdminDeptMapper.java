package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysAdminDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户与部门关联表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-27
 */
@Mapper
public interface SysAdminDeptMapper extends BaseMapper<SysAdminDept> {


    /**
     * 统计部门用户数
     *
     * @param deptId 部门ID
     * @return int
     */
    Integer countByDeptId(@Param("deptId") Long deptId);

    /**
     * 查询用户部门
     *
     * @param userId 用户ID
     * @return sysAdminDept
     */
    SysAdminDept selectByUserId(@Param("userId") Long userId);

    /**
     * 删除用户关联部门
     *
     * @param userId 用户ID
     * @return int
     */
    Integer deleteByUserId(@Param("userId") Long userId);

}
