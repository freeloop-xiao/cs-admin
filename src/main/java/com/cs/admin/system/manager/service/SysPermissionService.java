package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionEditDTO;
import com.cs.admin.system.manager.domain.dto.PermissionPageDTO;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.PermissionVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限编码信息表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 新增权限
     *
     * @param addDTO 权限新增dto
     * @return true/false
     */
    Boolean add(PermissionAddDTO addDTO);

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     * @return true/false
     */
    Boolean del(Long permissionId);

    /**
     * 修改权限
     *
     * @param editDTO 权限修改dto
     * @return true/false
     */
    Boolean edit(PermissionEditDTO editDTO);

    /**
     * 权限id查询详情
     *
     * @param permissionId 权限id
     * @return VO
     */
    PermissionVO getVO(Long permissionId);

    /**
     * 分页查权限信息
     *
     * @param pageDTO 分页查询请求信息
     * @return pageVO
     */
    PageVO<PermissionVO> page(PermissionPageDTO pageDTO);

    /**
     * 关键字列表查询
     *
     * @param key 关键字
     * @return list
     */
    List<PermissionVO> list(String key);

    /**
     * 通过权限id列表查询权限code Set
     * @param permissionIds 权限ID列表
     * @return list
     */
    List<SysPermission> list(Set<Long> permissionIds);

}
