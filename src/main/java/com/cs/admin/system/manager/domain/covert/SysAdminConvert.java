package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.AdminAddDTO;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.dto.AdminEditDTO;
import com.cs.admin.system.manager.domain.entity.SysAdmin;
import com.cs.admin.system.manager.domain.vo.AdminVO;
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
@Mapper(componentModel = "spring", uses = {SysDeptConvert.class, SysPostConvert.class, SysRoleConvert.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysAdminConvert extends BaseConvert<AdminAddDTO, SysAdmin, AdminVO> {

    /**
     * 编辑DTO转换 sysAdmin
     *
     * @param dto 传输对象
     * @return sysAdmin
     */
    SysAdmin toEntity(AdminEditDTO dto);

    /**
     * 查询用户综合信息转换为vo
     *
     * @param dto dto
     * @return adminVO
     */
    AdminVO toVO(AdminDTO dto);


    /**
     * vo转换在线DTO
     *
     * @param admin admin
     * @return dto
     */
    OnlineInfoDTO toDTO(AdminVO admin);


}
