package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.vo.DeptVO;
import com.cs.admin.system.manager.domain.vo.SubDeptVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysDeptConvertImpl implements SysDeptConvert {

    @Override
    public SysDept toEntity(DeptAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDept sysDept = new SysDept();

        sysDept.setDeptName( dto.getDeptName() );
        sysDept.setOrderNum( dto.getOrderNum() );
        sysDept.setLeader( dto.getLeader() );
        sysDept.setPhone( dto.getPhone() );
        sysDept.setEmail( dto.getEmail() );
        sysDept.setStatus( dto.getStatus() );
        sysDept.setParentId( dto.getParentId() );

        return sysDept;
    }

    @Override
    public DeptVO toVO(SysDept entity) {
        if ( entity == null ) {
            return null;
        }

        DeptVO deptVO = new DeptVO();

        deptVO.setDeptId( entity.getDeptId() );
        deptVO.setDeptName( entity.getDeptName() );
        deptVO.setOrderNum( entity.getOrderNum() );
        deptVO.setLeader( entity.getLeader() );
        deptVO.setPhone( entity.getPhone() );
        deptVO.setEmail( entity.getEmail() );
        deptVO.setStatus( entity.getStatus() );
        deptVO.setParentId( entity.getParentId() );

        return deptVO;
    }

    @Override
    public List<SysDept> toEntity(List<DeptAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysDept> list = new ArrayList<SysDept>( dtoList.size() );
        for ( DeptAddDTO deptAddDTO : dtoList ) {
            list.add( toEntity( deptAddDTO ) );
        }

        return list;
    }

    @Override
    public List<DeptVO> toVO(List<SysDept> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeptVO> list = new ArrayList<DeptVO>( entityList.size() );
        for ( SysDept sysDept : entityList ) {
            list.add( toVO( sysDept ) );
        }

        return list;
    }

    @Override
    public SysDept toEntity(DeptEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysDept sysDept = new SysDept();

        sysDept.setDeptId( dto.getDeptId() );
        sysDept.setDeptName( dto.getDeptName() );
        sysDept.setOrderNum( dto.getOrderNum() );
        sysDept.setLeader( dto.getLeader() );
        sysDept.setPhone( dto.getPhone() );
        sysDept.setEmail( dto.getEmail() );
        sysDept.setStatus( dto.getStatus() );
        sysDept.setParentId( dto.getParentId() );

        return sysDept;
    }

    @Override
    public SubDeptVO toSubVO(SysDept dept) {
        if ( dept == null ) {
            return null;
        }

        SubDeptVO subDeptVO = new SubDeptVO();

        subDeptVO.setDeptId( dept.getDeptId() );
        subDeptVO.setDeptName( dept.getDeptName() );

        return subDeptVO;
    }

    @Override
    public List<SubDeptVO> toSubVO(List<SysDept> deptList) {
        if ( deptList == null ) {
            return null;
        }

        List<SubDeptVO> list = new ArrayList<SubDeptVO>( deptList.size() );
        for ( SysDept sysDept : deptList ) {
            list.add( toSubVO( sysDept ) );
        }

        return list;
    }
}
