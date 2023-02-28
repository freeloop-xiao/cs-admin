package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.system.manager.domain.dto.AdminAddDTO;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.dto.AdminEditDTO;
import com.cs.admin.system.manager.domain.entity.SysAdmin;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import com.cs.admin.system.manager.domain.vo.SubDeptVO;
import com.cs.admin.system.manager.domain.vo.SubPostVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysAdminConvertImpl implements SysAdminConvert {

    @Autowired
    private SysDeptConvert sysDeptConvert;
    @Autowired
    private SysPostConvert sysPostConvert;
    @Autowired
    private SysRoleConvert sysRoleConvert;

    @Override
    public SysAdmin toEntity(AdminAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysAdmin sysAdmin = new SysAdmin();

        sysAdmin.setAccount( dto.getAccount() );
        sysAdmin.setPhone( dto.getPhone() );
        sysAdmin.setEmail( dto.getEmail() );
        sysAdmin.setPassword( dto.getPassword() );
        sysAdmin.setUserName( dto.getUserName() );
        sysAdmin.setNickName( dto.getNickName() );
        sysAdmin.setAvatar( dto.getAvatar() );
        sysAdmin.setSex( dto.getSex() );
        sysAdmin.setBirthday( dto.getBirthday() );
        sysAdmin.setIsLocked( dto.getIsLocked() );

        return sysAdmin;
    }

    @Override
    public AdminVO toVO(SysAdmin entity) {
        if ( entity == null ) {
            return null;
        }

        AdminVO adminVO = new AdminVO();

        adminVO.setUserId( entity.getUserId() );
        adminVO.setAccount( entity.getAccount() );
        adminVO.setPhone( entity.getPhone() );
        adminVO.setEmail( entity.getEmail() );
        adminVO.setUserName( entity.getUserName() );
        adminVO.setNickName( entity.getNickName() );
        adminVO.setAvatar( entity.getAvatar() );
        adminVO.setSex( entity.getSex() );
        adminVO.setBirthday( entity.getBirthday() );
        adminVO.setIsLocked( entity.getIsLocked() );
        adminVO.setPwdResetTime( entity.getPwdResetTime() );
        adminVO.setCreateTime( entity.getCreateTime() );

        return adminVO;
    }

    @Override
    public List<SysAdmin> toEntity(List<AdminAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysAdmin> list = new ArrayList<SysAdmin>( dtoList.size() );
        for ( AdminAddDTO adminAddDTO : dtoList ) {
            list.add( toEntity( adminAddDTO ) );
        }

        return list;
    }

    @Override
    public List<AdminVO> toVO(List<SysAdmin> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AdminVO> list = new ArrayList<AdminVO>( entityList.size() );
        for ( SysAdmin sysAdmin : entityList ) {
            list.add( toVO( sysAdmin ) );
        }

        return list;
    }

    @Override
    public SysAdmin toEntity(AdminEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysAdmin sysAdmin = new SysAdmin();

        sysAdmin.setUserId( dto.getUserId() );
        sysAdmin.setAccount( dto.getAccount() );
        sysAdmin.setPhone( dto.getPhone() );
        sysAdmin.setEmail( dto.getEmail() );
        sysAdmin.setUserName( dto.getUserName() );
        sysAdmin.setNickName( dto.getNickName() );
        sysAdmin.setAvatar( dto.getAvatar() );
        sysAdmin.setSex( dto.getSex() );
        sysAdmin.setBirthday( dto.getBirthday() );
        sysAdmin.setIsLocked( dto.getIsLocked() );

        return sysAdmin;
    }

    @Override
    public AdminVO toVO(AdminDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AdminVO adminVO = new AdminVO();

        adminVO.setUserId( dto.getUserId() );
        adminVO.setAccount( dto.getAccount() );
        adminVO.setPhone( dto.getPhone() );
        adminVO.setEmail( dto.getEmail() );
        adminVO.setUserName( dto.getUserName() );
        adminVO.setNickName( dto.getNickName() );
        adminVO.setAvatar( dto.getAvatar() );
        adminVO.setSex( dto.getSex() );
        adminVO.setBirthday( dto.getBirthday() );
        adminVO.setDeptList( sysDeptConvert.toSubVO( dto.getDeptList() ) );
        adminVO.setPostList( sysPostConvert.toSubVO( dto.getPostList() ) );
        adminVO.setRoleList( sysRoleConvert.toSubRoleVO( dto.getRoleList() ) );
        adminVO.setIsLocked( dto.getIsLocked() );
        adminVO.setPwdResetTime( dto.getPwdResetTime() );
        adminVO.setCreateTime( dto.getCreateTime() );

        return adminVO;
    }

    @Override
    public OnlineInfoDTO toDTO(AdminVO admin) {
        if ( admin == null ) {
            return null;
        }

        OnlineInfoDTO onlineInfoDTO = new OnlineInfoDTO();

        onlineInfoDTO.setUserId( admin.getUserId() );
        onlineInfoDTO.setAccount( admin.getAccount() );
        onlineInfoDTO.setPhone( admin.getPhone() );
        onlineInfoDTO.setEmail( admin.getEmail() );
        onlineInfoDTO.setUserName( admin.getUserName() );
        onlineInfoDTO.setNickName( admin.getNickName() );
        List<SubDeptVO> list = admin.getDeptList();
        if ( list != null ) {
            onlineInfoDTO.setDeptList( new ArrayList<SubDeptVO>( list ) );
        }
        List<SubPostVO> list1 = admin.getPostList();
        if ( list1 != null ) {
            onlineInfoDTO.setPostList( new ArrayList<SubPostVO>( list1 ) );
        }
        List<SubRoleVO> list2 = admin.getRoleList();
        if ( list2 != null ) {
            onlineInfoDTO.setRoleList( new ArrayList<SubRoleVO>( list2 ) );
        }
        onlineInfoDTO.setIsLocked( admin.getIsLocked() );
        onlineInfoDTO.setPwdResetTime( admin.getPwdResetTime() );

        return onlineInfoDTO;
    }
}
