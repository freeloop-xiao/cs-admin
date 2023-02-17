package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.vo.DeptVO;
import com.cs.admin.system.manager.domain.vo.SubDeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

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
public interface SysDeptConvert extends BaseConvert<DeptAddDTO, SysDept, DeptVO> {

    /**
     * 编辑DTO转换 sysDept
     *
     * @param dto 传输对象
     * @return sysDept
     */
    SysDept toEntity(DeptEditDTO dto);

    /**
     * sysDept转换为subDeptVO
     *
     * @param dept dept
     * @return subDeptVO
     */
    SubDeptVO toSubVO(SysDept dept);

    /**
     * 部门列表转换subDeptVO
     * @param deptList 部门列表
     * @return list
     */
    List<SubDeptVO> toSubVO(List<SysDept> deptList);
}
