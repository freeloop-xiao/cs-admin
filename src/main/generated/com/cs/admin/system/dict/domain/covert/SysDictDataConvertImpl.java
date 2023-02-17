package com.cs.admin.system.dict.domain.covert;

import com.cs.admin.system.dict.domain.dto.DictDataAddDTO;
import com.cs.admin.system.dict.domain.dto.DictDataEditDTO;
import com.cs.admin.system.dict.domain.entity.SysDictData;
import com.cs.admin.system.dict.domain.vo.DictDataVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysDictDataConvertImpl implements SysDictDataConvert {

    @Override
    public SysDictData toEntity(DictDataAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDictData sysDictData = new SysDictData();

        sysDictData.setDictCode( dto.getDictCode() );
        sysDictData.setDictSort( dto.getDictSort() );
        sysDictData.setDictLabel( dto.getDictLabel() );
        sysDictData.setDictValue( dto.getDictValue() );
        sysDictData.setIsDefault( dto.getIsDefault() );
        sysDictData.setParentDataId( dto.getParentDataId() );
        sysDictData.setStatus( dto.getStatus() );
        sysDictData.setRemark( dto.getRemark() );

        return sysDictData;
    }

    @Override
    public DictDataVO toVO(SysDictData entity) {
        if ( entity == null ) {
            return null;
        }

        DictDataVO dictDataVO = new DictDataVO();

        dictDataVO.setDataId( entity.getDataId() );
        dictDataVO.setDictCode( entity.getDictCode() );
        dictDataVO.setDictSort( entity.getDictSort() );
        dictDataVO.setDictLabel( entity.getDictLabel() );
        dictDataVO.setDictValue( entity.getDictValue() );
        dictDataVO.setIsDefault( entity.getIsDefault() );
        dictDataVO.setParentDataId( entity.getParentDataId() );
        dictDataVO.setStatus( entity.getStatus() );
        dictDataVO.setRemark( entity.getRemark() );

        return dictDataVO;
    }

    @Override
    public List<SysDictData> toEntity(List<DictDataAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysDictData> list = new ArrayList<SysDictData>( dtoList.size() );
        for ( DictDataAddDTO dictDataAddDTO : dtoList ) {
            list.add( toEntity( dictDataAddDTO ) );
        }

        return list;
    }

    @Override
    public List<DictDataVO> toVO(List<SysDictData> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DictDataVO> list = new ArrayList<DictDataVO>( entityList.size() );
        for ( SysDictData sysDictData : entityList ) {
            list.add( toVO( sysDictData ) );
        }

        return list;
    }

    @Override
    public SysDictData toEntity(DictDataEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDictData sysDictData = new SysDictData();

        sysDictData.setDataId( dto.getDataId() );
        sysDictData.setDictCode( dto.getDictCode() );
        sysDictData.setDictSort( dto.getDictSort() );
        sysDictData.setDictLabel( dto.getDictLabel() );
        sysDictData.setDictValue( dto.getDictValue() );
        sysDictData.setIsDefault( dto.getIsDefault() );
        sysDictData.setParentDataId( dto.getParentDataId() );
        sysDictData.setStatus( dto.getStatus() );
        sysDictData.setRemark( dto.getRemark() );

        return sysDictData;
    }
}
