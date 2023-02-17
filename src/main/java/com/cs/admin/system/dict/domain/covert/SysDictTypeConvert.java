package com.cs.admin.system.dict.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.dict.domain.dto.DictTypeAddDTO;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;
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
public interface SysDictTypeConvert extends BaseConvert<DictTypeAddDTO, SysDictType, DictTypeVO> {

    /**
     * 编辑DTO转换 SysDictType
     *
     * @param dto 传输对象
     * @return SysDictType
     */
    SysDictType toEntity(DictTypeEditDTO dto);
}
