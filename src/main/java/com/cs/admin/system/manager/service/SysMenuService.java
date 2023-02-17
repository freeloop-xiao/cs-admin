package com.cs.admin.system.manager.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.dto.MenuPageDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.vo.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 查询用户菜单列表初始化菜单首页
     *
     * @param userId 用户ID
     * @return list tree
     */
    List<Tree<Long>> build(Long userId);

    /**
     * 新增菜单
     *
     * @param addDTO 新增菜单传输对象
     * @return true/false
     */
    Boolean add(MenuAddDTO addDTO);


    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return true/false
     */
    Boolean del(Long menuId);

    /**
     * 编辑菜单
     *
     * @param editDTO 菜单编辑传输对象
     * @return true/false
     */
    Boolean edit(MenuEditDTO editDTO);

    /**
     * 获取单个菜单详情
     *
     * @param menuId 菜单id
     * @return menuVO
     */
    MenuVO getMenuVO(Long menuId);

    /**
     * 分页查询菜单列表
     *
     * @param pageDTO 菜单分页传输对象
     * @return pageVO
     */
    PageVO<MenuVO> page(MenuPageDTO pageDTO);

    /**
     * 父菜单查询子菜单列表
     *
     * @param parentMenuId 父菜单ID
     * @return list
     */
    List<MenuVO> list(Long parentMenuId);


    /**
     * 查询系统菜单树
     *
     * @return tree
     */
    List<Tree<Long>> tree();


    /**
     * id列表查询菜单列表
     *
     * @param menuIds id列表
     * @return list
     */
    List<SysMenu> getMenusByIds(Set<Long> menuIds);

    /**
     * 角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return list
     */
    List<Tree<Long>> getMenusByRoleId(Long roleId);


}
