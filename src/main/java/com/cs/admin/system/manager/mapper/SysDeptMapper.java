package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 通过父id和名称查询部门
     *
     * @param parentId 父部门ID
     * @param deptName 部门名称
     * @return dept
     */
    SysDept selectByParentIdAndDeptName(@Param("parentId") Long parentId,
                                        @Param("deptName") String deptName);

    /**
     * 统计部门下有多少子部门
     *
     * @param parentId 父部门ID
     * @return int
     */
    Integer countByParentId(@Param("parentId") Long parentId);


}
