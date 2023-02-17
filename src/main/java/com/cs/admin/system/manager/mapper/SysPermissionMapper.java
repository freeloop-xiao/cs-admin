package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限编码信息表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 权限标识查询权限信息
     *
     * @param code 权限code
     * @return sysPermission
     */
    SysPermission selectByCode(@Param("code") String code);

    /**
     * 通过权限名称查询
     *
     * @param name 权限名称
     * @return sysPermission
     */
    SysPermission selectByName(@Param("name") String name);
}
