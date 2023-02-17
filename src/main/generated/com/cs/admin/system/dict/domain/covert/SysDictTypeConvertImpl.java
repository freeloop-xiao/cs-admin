package com.cs.admin.system.dict.domain.covert;

import com.cs.admin.system.dict.domain.dto.DictTypeAddDTO;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysDictTypeConvertImpl implements SysDictTypeConvert {

    @Override
    public SysDictType toEntity(DictTypeAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDictType sysDictType = new SysDictType();

        sysDictType.setDictName( dto.getDictName() );
        sysDictType.setDictCode( dto.getDictCode() );
        sysDictType.setStatus( dto.getStatus() );
        sysDictType.setRemark( dto.getRemark() );

        return sysDictType;
    }

    @Override
    public DictTypeVO toVO(SysDictType entity) {
        if ( entity == null ) {
            return null;
        }

        DictTypeVO dictTypeVO = new DictTypeVO();

        dictTypeVO.setDictId( entity.getDictId() );
        dictTypeVO.setDictName( entity.getDictName() );
        dictTypeVO.setDictCode( entity.getDictCode() );
        dictTypeVO.setStatus( entity.getStatus() );
        dictTypeVO.setRemark( entity.getRemark() );

        return dictTypeVO;
    }

    @Override
    public List<SysDictType> toEntity(List<DictTypeAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysDictType> list = new ArrayList<SysDictType>( dtoList.size() );
        for ( DictTypeAddDTO dictTypeAddDTO : dtoList ) {
            list.add( toEntity( dictTypeAddDTO ) );
        }

        return list;
    }

    @Override
    public List<DictTypeVO> toVO(List<SysDictType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DictTypeVO> list = new ArrayList<DictTypeVO>( entityList.size() );
        for ( SysDictType sysDictType : entityList ) {
            list.add( toVO( sysDictType ) );
        }

        return list;
    }

    @Override
    public SysDictType toEntity(DictTypeEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDictType sysDictType = new SysDictType();

        sysDictType.setDictId( dto.getDictId() );
        sysDictType.setDictName( dto.getDictName() );
        sysDictType.setDictCode( dto.getDictCode() );
        sysDictType.setStatus( dto.getStatus() );
        sysDictType.setRemark( dto.getRemark() );

        return sysDictType;
    }
}
