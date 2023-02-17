package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.entity.SysRole;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/22 22:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleConvert extends BaseConvert<RoleAddDTO, SysRole, RoleVO> {

    /**
     * 编辑DTO转换 SysRole
     *
     * @param dto 传输对象
     * @return sysRole
     */
    SysRole toEntity(RoleEditDTO dto);

    /**
     * 系统entity转换为VO
     *
     * @param sysRole 角色
     * @return subRoleVO
     */
    SubRoleVO toSubRoleVO(SysRole sysRole);

    /**
     * list系统entity转换为VO
     * @param roleList list
     * @return list
     */
    List<SubRoleVO> toSubRoleVO(List<SysRole> roleList);
}
