package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysMenuConvert;
import com.cs.admin.system.manager.domain.covert.SysPermissionConvert;
import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.dto.MenuPageDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.MenuVO;
import com.cs.admin.system.manager.mapper.SysMenuMapper;
import com.cs.admin.system.manager.service.SysMenuService;
import com.cs.admin.system.manager.service.SysPermissionService;
import com.cs.admin.system.manager.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuConvert sysMenuConvert;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysPermissionConvert sysPermissionConvert;

    @Override
    public List<Tree<Long>> build(Long userId) {
        List<MenuVO> list = sysMenuConvert.toVO(getBaseMapper().selectByUserId(userId));
        List<TreeNode<Long>> nodes = list.stream().map(this::mapToTreeNode).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L, getTreeNodeConfig(), new DefaultNodeParser<>());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(MenuAddDTO addDTO) {
        checkAdd(addDTO);
        return save(sysMenuConvert.toEntity(addDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long menuId) {
        checkDel(menuId);
        return removeById(menuId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean edit(MenuEditDTO editDTO) {

        checkEdit(editDTO);

        SysMenu sysMenu = sysMenuConvert.toEntity(editDTO);
        sysMenu.setUpdateBy(WebUtil.getUserId());

        return updateById(sysMenu);
    }

    @Override
    public MenuVO getMenuVO(Long menuId) {
        MenuVO menuVO = sysMenuConvert.toVO(getById(menuId));
        if (StrUtil.isEmpty(menuVO.getPermission())){
            return menuVO;
        }
        Set<Long> permissionIds = Arrays.stream(menuVO.getPermission().split(","))
                .filter(StrUtil::isNotEmpty)
                .map(Long::valueOf)
                .collect(Collectors.toSet());
        List<SysPermission> permissions = sysPermissionService.list(permissionIds);
        menuVO.setPermissions(sysPermissionConvert.toVO(permissions));
        return menuVO;
    }

    @Override
    public PageVO<MenuVO> page(MenuPageDTO pageDTO) {

        Page<SysMenu> pageCondition = PageUtil.page(pageDTO);

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        // 父id
        if (ObjectUtil.isNotNull(pageDTO.getParentMenuId())) {
            queryWrapper.eq("parent_menu_id", pageDTO.getParentMenuId());
        } else {
            queryWrapper.eq("parent_menu_id", 0L);
        }

        if (ObjectUtil.isNotNull(pageDTO.getType())) {
            queryWrapper.eq("type", pageDTO.getType());
        }
        if (ObjectUtil.isNotNull(pageDTO.getIsFrame())) {
            queryWrapper.eq("is_frame", pageDTO.getIsFrame());
        }
        if (ObjectUtil.isNotNull(pageDTO.getHidden())) {
            queryWrapper.eq("hidden", pageDTO.getHidden());
        }
        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        // 关键字搜索 标题、名称、组件、权限、路径模糊查询
        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("title", pageDTO.getKey())
                    .or().like("name", pageDTO.getKey())
                    .or().like("component", pageDTO.getKey())
                    .or().like("permission", pageDTO.getKey())
                    .or().like("path", pageDTO.getKey()));
        }

        Page<SysMenu> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysMenuConvert.toVO(result.getRecords()));
    }

    @Override
    public List<MenuVO> list(Long parentMenuId) {
        return sysMenuConvert.toVO(getBaseMapper().listByParentMenuId(parentMenuId));
    }


    @Override
    public List<Tree<Long>> tree() {
        List<TreeNode<Long>> nodes = list().stream().map(this::mapToTreeNode).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L, getTreeNodeConfig(), new DefaultNodeParser<>());
    }

    @Override
    public List<SysMenu> getMenusByIds(Set<Long> menuIds) {

        if (ObjectUtil.isNull(menuIds) || menuIds.size() == 0) {
            return null;
        }

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("menu_id", menuIds);

        return list(queryWrapper);
    }


    @Override
    public List<Tree<Long>> getMenusByRoleId(Long roleId) {
        List<SysMenu> list = getBaseMapper().selectListByRoleId(roleId);
        if (ObjectUtil.isNull(list) || list.size() == 0) {
            return null;
        }
        List<TreeNode<Long>> nodes = list.stream().map(this::mapToTreeNode).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L, getTreeNodeConfig(), new DefaultNodeParser<>());
    }


    /**
     * 检查新增菜单
     *
     * @param addDTO 新增传输对象
     */
    private void checkAdd(MenuAddDTO addDTO) {

        // 检查title是否已经存在
        checkTitle(addDTO.getTitle(), addDTO.getType(), addDTO.getParentMenuId());

        // 检查菜单是否存在
        checkParentMenu(addDTO.getParentMenuId());

        // 更新父菜单subCount
        updateParentSubCount(addDTO.getParentMenuId(), 1);

    }

    /**
     * 检查菜单标题
     *
     * @param title    标题
     * @param type     类型
     * @param parentId 父ID
     */
    private void checkTitle(String title, Integer type, Long parentId) {
        SysMenu menu = getBaseMapper().selectByTitleAndTypeAndParentId(title, type, parentId);
        if (ObjectUtil.isNotNull(menu)) {
            ReportUtil.throwEx(StrUtil.format("菜单title[{}]已经存在!", title));
        }
    }


    /**
     * 检查父菜单
     *
     * @param parentMenuId 父菜单ID
     */
    private void checkParentMenu(Long parentMenuId) {

        if (ObjectUtil.isNull(parentMenuId) || parentMenuId == 0L) {
            return;
        }

        SysMenu menu = getById(parentMenuId);
        if (ObjectUtil.isNull(menu)) {
            ReportUtil.throwEx(StrUtil.format("父菜单ID[{}]不存在!", parentMenuId));
        }
    }


    /**
     * 删除菜单检查
     *
     * @param menuId 菜单ID
     */
    private void checkDel(Long menuId) {

        SysMenu menu = getById(menuId);

        if (ObjectUtil.isNull(menu)) {
            ReportUtil.throwEx(StrUtil.format("菜单ID[{}]不存在!", menuId));
        }

        // 检查是否有角色管理该菜单
        if (sysRoleMenuService.countByMenuId(menuId) > 0) {
            ReportUtil.throwEx(StrUtil.format("菜单ID[{}]关联角色,不能删除(如需删除请先解除关联)!", menuId));
        }
        // 更新父菜单subCount
        updateParentSubCount(menu.getParentMenuId(), -1);
    }


    /**
     * 菜单编辑检查
     *
     * @param editDTO 编辑传输对象
     */
    private void checkEdit(MenuEditDTO editDTO) {

        SysMenu menu = getById(editDTO.getMenuId());

        if (ObjectUtil.isNull(menu)) {
            ReportUtil.throwEx(StrUtil.format("菜单ID[{}]不存在!", editDTO.getMenuId()));
        }

        // 父菜单是否存在
        checkParentMenu(editDTO.getParentMenuId());

        // 判断是否修改父菜单 或者 类型
        if (!menu.getParentMenuId().equals(editDTO.getParentMenuId())
                || !menu.getType().equals(editDTO.getType())) {
            checkTitle(editDTO.getTitle(), editDTO.getType(), editDTO.getParentMenuId());
        } else {

            // 仅仅修改title
            if (!menu.getTitle().equals(editDTO.getTitle())) {
                checkTitle(editDTO.getTitle(), editDTO.getType(), editDTO.getParentMenuId());
            }

        }

        // 原父菜单减1
        updateParentSubCount(menu.getParentMenuId(), -1);

        // 新父菜单增1
        updateParentSubCount(editDTO.getParentMenuId(), 1);
    }


    /**
     * 父菜单增减subCount
     *
     * @param parentMenuId 父菜单ID
     * @param num          数量
     */
    private void updateParentSubCount(Long parentMenuId, Integer num) {
        if (parentMenuId != 0L) {
            getBaseMapper().updateSubCount(parentMenuId, num);
        }
    }


    /**
     * 转换
     *
     * @param sysMenu sysMenu
     * @return treeNode
     */
    private TreeNode<Long> mapToTreeNode(SysMenu sysMenu) {
        TreeNode<Long> treeNode = new TreeNode<>(sysMenu.getMenuId(), sysMenu.getParentMenuId(),
                sysMenu.getTitle(), sysMenu.getMenuSort());
        treeNode.setExtra(getMap(sysMenu));
        return treeNode;
    }

    /**
     * 转换
     *
     * @param menuVO menu vo
     * @return treeNode
     */
    private TreeNode<Long> mapToTreeNode(MenuVO menuVO) {
        TreeNode<Long> treeNode = new TreeNode<>(menuVO.getMenuId(), menuVO.getParentMenuId(), menuVO.getTitle(), menuVO.getMenuSort());
        treeNode.setExtra(getMap(menuVO));
        return treeNode;
    }

    /**
     * 获取其他属性
     *
     * @param obj obj
     * @return map
     */
    public Map<String, Object> getMap(Object obj) {

        Map<String, Object> map = BeanUtil.beanToMap(obj, false, true);

        map.remove("name");
        map.remove("menuId");
        map.remove("parentMenuId");
        map.remove("orderNum");
        return map;
    }

    /**
     * 查询TreeNodeConfig
     *
     * @return treeNodeConfig
     */
    private TreeNodeConfig getTreeNodeConfig() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("menuSort");
        treeNodeConfig.setIdKey("menuId");
        treeNodeConfig.setNameKey("name");
        treeNodeConfig.setParentIdKey("parentMenuId");
        treeNodeConfig.setDeep(15);
        return treeNodeConfig;
    }
}
