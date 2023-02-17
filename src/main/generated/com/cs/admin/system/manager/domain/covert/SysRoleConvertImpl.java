package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.entity.SysRole;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysRoleConvertImpl implements SysRoleConvert {

    @Override
    public SysRole toEntity(RoleAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setRoleCode( dto.getRoleCode() );
        sysRole.setRoleName( dto.getRoleName() );
        sysRole.setDescription( dto.getDescription() );
        sysRole.setDataScope( dto.getDataScope() );
        sysRole.setOrderNum( dto.getOrderNum() );
        sysRole.setStatus( dto.getStatus() );
        sysRole.setRemark( dto.getRemark() );

        return sysRole;
    }

    @Override
    public RoleVO toVO(SysRole entity) {
        if ( entity == null ) {
            return null;
        }

        RoleVO roleVO = new RoleVO();

        roleVO.setRoleId( entity.getRoleId() );
        roleVO.setRoleCode( entity.getRoleCode() );
        roleVO.setRoleName( entity.getRoleName() );
        roleVO.setDescription( entity.getDescription() );
        roleVO.setDataScope( entity.getDataScope() );
        roleVO.setOrderNum( entity.getOrderNum() );
        roleVO.setStatus( entity.getStatus() );
        roleVO.setRemark( entity.getRemark() );

        return roleVO;
    }

    @Override
    public List<SysRole> toEntity(List<RoleAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysRole> list = new ArrayList<SysRole>( dtoList.size() );
        for ( RoleAddDTO roleAddDTO : dtoList ) {
            list.add( toEntity( roleAddDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleVO> toVO(List<SysRole> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleVO> list = new ArrayList<RoleVO>( entityList.size() );
        for ( SysRole sysRole : entityList ) {
            list.add( toVO( sysRole ) );
        }

        return list;
    }

    @Override
    public SysRole toEntity(RoleEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysRole sysRole = new SysRole();

        sysRole.setRoleId( dto.getRoleId() );
        sysRole.setRoleCode( dto.getRoleCode() );
        sysRole.setRoleName( dto.getRoleName() );
        sysRole.setDescription( dto.getDescription() );
        sysRole.setDataScope( dto.getDataScope() );
        sysRole.setOrderNum( dto.getOrderNum() );
        sysRole.setStatus( dto.getStatus() );
        sysRole.setRemark( dto.getRemark() );

        return sysRole;
    }

    @Override
    public SubRoleVO toSubRoleVO(SysRole sysRole) {
        if ( sysRole == null ) {
            return null;
        }

        SubRoleVO subRoleVO = new SubRoleVO();

        subRoleVO.setRoleId( sysRole.getRoleId() );
        subRoleVO.setRoleCode( sysRole.getRoleCode() );
        subRoleVO.setRoleName( sysRole.getRoleName() );

        return subRoleVO;
    }

    @Override
    public List<SubRoleVO> toSubRoleVO(List<SysRole> roleList) {
        if ( roleList == null ) {
            return null;
        }

        List<SubRoleVO> list = new ArrayList<SubRoleVO>( roleList.size() );
        for ( SysRole sysRole : roleList ) {
            list.add( toSubRoleVO( sysRole ) );
        }

        return list;
    }
}
