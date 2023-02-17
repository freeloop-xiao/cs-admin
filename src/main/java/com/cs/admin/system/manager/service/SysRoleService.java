package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.dto.RolePageDTO;
import com.cs.admin.system.manager.domain.entity.SysRole;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 新增角色
     *
     * @param addDTO 角色传输对象
     * @return true/false
     */
    Boolean add(RoleAddDTO addDTO);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return true/false
     */
    Boolean del(Long roleId);

    /**
     * 编辑角色
     *
     * @param editDTO 角色DTO
     * @return true/false
     */
    Boolean edit(RoleEditDTO editDTO);

    /**
     * 获取角色详情
     *
     * @param roleId 角色ID
     * @return roleVO
     */
    RoleVO getVO(Long roleId);

    /**
     * 分页查询角色列表
     *
     * @param pageDTO 分页查询DTO
     * @return pageVO
     */
    PageVO<RoleVO> page(RolePageDTO pageDTO);


    /**
     * 关键字查询列表
     *
     * @param key 关键字
     * @return list
     */
    List<RoleVO> list(String key);

    /**
     * 查询角色列表
     *
     * @param roleIds 角色id列表
     * @return list
     */
    List<SysRole> getByRoleIds(Set<Long> roleIds);

    /**
     * 用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return list
     */
    List<RoleVO> getByUserId(Long userId);

    /**
     * 用户ID查询角色SubRoleVO
     *
     * @param userId 用户ID
     * @return list
     */
    List<SubRoleVO> getSubRoleByUserId(Long userId);
}
