package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 统计菜单授权角色个数
     *
     * @param menuId 菜单ID
     * @return int
     */
    Integer countByMenuId(@Param("menuId") Long menuId);

}
