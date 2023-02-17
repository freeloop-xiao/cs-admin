package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 父菜单查询子菜单列表
     *
     * @param parentMenuId 父菜单ID
     * @return list
     */
    List<SysMenu> listByParentMenuId(Long parentMenuId);

    /**
     * 更新菜单 subCount
     *
     * @param menuId 菜单ID
     * @param num    数量
     * @return true/false
     */
    Boolean updateSubCount(@Param("menuId") Long menuId, @Param("num") Integer num);

    /**
     * 用户ID查询菜单列表
     *
     * @param userId userId
     * @return list
     */
    List<SysMenu> selectByUserId(Long userId);

    /**
     * 角色ID查询菜单ID
     *
     * @param roleId 角色ID
     * @return lsit
     */
    List<SysMenu> selectListByRoleId(@Param("roleId") Long roleId);


    /**
     * 通过title信息查询菜单
     *
     * @param title    title
     * @param type     类型
     * @param parentId 父菜单ID
     * @return sysMenu
     */
    SysMenu selectByTitleAndTypeAndParentId(@Param("title") String title,
                                            @Param("type") Integer type,
                                            @Param("parentId") Long parentId);

    /**
     * 通name信息查询菜单
     *
     * @param name     name
     * @param type     类型
     * @param parentId 父菜单ID
     * @return sysMenu
     */
    SysMenu selectByNameAndTypeAndParentId(@Param("name") String name,
                                           @Param("type") Integer type,
                                           @Param("parentId") Long parentId);

}
