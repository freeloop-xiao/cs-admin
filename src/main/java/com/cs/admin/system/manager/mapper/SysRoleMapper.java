package com.cs.admin.system.manager.mapper;

import com.cs.admin.system.manager.domain.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     * @param userId 用户ID
     * @return list
     */
    List<SysRole> selectRolesByUserId(@Param("userId") Long userId);

}
