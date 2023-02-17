package com.cs.admin.system.manager.mapper;

import com.cs.admin.system.manager.domain.entity.SysAdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    /**
     * 统计角色关联用户数
     * @param roleId 角色Id
     * @return int
     */
    Integer countByRoleId(@Param("roleId") Long roleId);

    /**
     * 删除用户橘色
     * @param userId 用户ID
     * @return int
     */
    Integer deleteByUserId(@Param("userId") Long userId);
}
