package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionEditDTO;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.PermissionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysPermissionConvertImpl implements SysPermissionConvert {

    @Override
    public SysPermission toEntity(PermissionAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysPermission sysPermission = new SysPermission();

        sysPermission.setName( dto.getName() );
        sysPermission.setCode( dto.getCode() );
        sysPermission.setUrl( dto.getUrl() );
        sysPermission.setDescription( dto.getDescription() );
        sysPermission.setStatus( dto.getStatus() );
        sysPermission.setRemark( dto.getRemark() );

        return sysPermission;
    }

    @Override
    public PermissionVO toVO(SysPermission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionVO permissionVO = new PermissionVO();

        permissionVO.setPermissionId( entity.getPermissionId() );
        permissionVO.setName( entity.getName() );
        permissionVO.setCode( entity.getCode() );
        permissionVO.setUrl( entity.getUrl() );
        permissionVO.setDescription( entity.getDescription() );
        permissionVO.setStatus( entity.getStatus() );
        permissionVO.setRemark( entity.getRemark() );

        return permissionVO;
    }

    @Override
    public List<SysPermission> toEntity(List<PermissionAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysPermission> list = new ArrayList<SysPermission>( dtoList.size() );
        for ( PermissionAddDTO permissionAddDTO : dtoList ) {
            list.add( toEntity( permissionAddDTO ) );
        }

        return list;
    }

    @Override
    public List<PermissionVO> toVO(List<SysPermission> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PermissionVO> list = new ArrayList<PermissionVO>( entityList.size() );
        for ( SysPermission sysPermission : entityList ) {
            list.add( toVO( sysPermission ) );
        }

        return list;
    }

    @Override
    public SysPermission toEntity(PermissionEditDTO editDTO) {
        if ( editDTO == null ) {
            return null;
        }

        SysPermission sysPermission = new SysPermission();

        sysPermission.setPermissionId( editDTO.getPermissionId() );
        sysPermission.setName( editDTO.getName() );
        sysPermission.setCode( editDTO.getCode() );
        sysPermission.setUrl( editDTO.getUrl() );
        sysPermission.setDescription( editDTO.getDescription() );
        sysPermission.setStatus( editDTO.getStatus() );
        sysPermission.setRemark( editDTO.getRemark() );

        return sysPermission;
    }
}
