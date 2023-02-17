package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.vo.MenuVO;
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
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMenuConvert extends BaseConvert<MenuAddDTO, SysMenu, MenuVO> {

    /**
     * 编辑DTO转换 SysMenu
     *
     * @param dto 传输对象
     * @return sysMenu
     */
    SysMenu toEntity(MenuEditDTO dto);
}
