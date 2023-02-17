package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionEditDTO;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.PermissionVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/22 22:01
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPermissionConvert extends BaseConvert<PermissionAddDTO, SysPermission, PermissionVO> {

    /**
     * 修改DTO 转 Entity
     *
     * @param editDTO dto
     * @return entity
     */
    SysPermission toEntity(PermissionEditDTO editDTO);
}
