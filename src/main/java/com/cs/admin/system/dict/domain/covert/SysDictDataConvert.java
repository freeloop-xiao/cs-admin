package com.cs.admin.system.dict.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.dict.domain.dto.DictDataAddDTO;
import com.cs.admin.system.dict.domain.dto.DictDataEditDTO;
import com.cs.admin.system.dict.domain.entity.SysDictData;
import com.cs.admin.system.dict.domain.vo.DictDataVO;
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
public interface SysDictDataConvert extends BaseConvert<DictDataAddDTO, SysDictData, DictDataVO> {

    /**
     * 编辑DTO转换 SysDictData
     *
     * @param dto 传输对象
     * @return SysDictData
     */
    SysDictData toEntity(DictDataEditDTO dto);
}
