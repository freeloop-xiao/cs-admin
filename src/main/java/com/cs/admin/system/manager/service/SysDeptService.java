package com.cs.admin.system.manager.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.dto.DeptPageDTO;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.vo.DeptVO;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 新增部门
     *
     * @param addDTO 部门新增传输对象
     * @return true/false
     */
    Boolean add(DeptAddDTO addDTO);

    /**
     * 删除部门
     *
     * @param deptId 部门Id
     * @return true/false
     */
    Boolean del(Long deptId);

    /**
     * 编辑部门
     *
     * @param editDTO 编辑部门传输对象
     * @return true/false
     */
    Boolean edit(DeptEditDTO editDTO);

    /**
     * 获取单个部门详情
     *
     * @param deptId 部门ID
     * @return deptVO
     */
    DeptVO getVO(Long deptId);

    /**
     * 分页查询部门
     *
     * @param pageDTO 部门分页查询传输对象
     * @return pageVO
     */
    PageVO<DeptVO> page(DeptPageDTO pageDTO);

    /**
     * 查询部门树
     *
     * @return tree
     */
    List<Tree<Long>> tree();


}
