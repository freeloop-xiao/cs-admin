package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysDeptConvert;
import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.dto.DeptPageDTO;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.vo.DeptVO;
import com.cs.admin.system.manager.mapper.SysDeptMapper;
import com.cs.admin.system.manager.service.SysAdminDeptService;
import com.cs.admin.system.manager.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptConvert sysDeptConvert;


    @Autowired
    private SysAdminDeptService sysAdminDeptService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(DeptAddDTO addDTO) {
        checkAdd(addDTO);
        return save(sysDeptConvert.toEntity(addDTO));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long deptId) {

        if (sysAdminDeptService.getCountByDeptId(deptId) > 0) {
            ReportUtil.throwEx(StrUtil.format("部门ID[{}]已关联用户,不能删除(如需删除请先取消关联)!", deptId));
        }

        if (getBaseMapper().countByParentId(deptId) > 0) {
            ReportUtil.throwEx(StrUtil.format("部门ID[{}]拥有子部门,不能删除(如需删除请先删除子部门)!", deptId));
        }

        return removeById(deptId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean edit(DeptEditDTO editDTO) {

        checkEdit(editDTO);

        SysDept sysDept = sysDeptConvert.toEntity(editDTO);
        sysDept.setUpdateBy(WebUtil.getUserId());

        return updateById(sysDept);
    }

    @Override
    public DeptVO getVO(Long deptId) {
        return sysDeptConvert.toVO(getById(deptId));
    }

    @Override
    public PageVO<DeptVO> page(DeptPageDTO pageDTO) {

        Page<SysDept> pageCondition = PageUtil.page(pageDTO);

        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();

        if (ObjectUtil.isNotNull(pageDTO.getParentId())) {
            queryWrapper.eq("parent_id", pageDTO.getParentId());
        }

        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("dept_name", pageDTO.getKey())
                    .or().like("phone", pageDTO.getKey())
                    .or().like("email", pageDTO.getKey()));
        }

        Page<SysDept> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysDeptConvert.toVO(result.getRecords()));
    }


    @Override
    public List<Tree<Long>> tree() {

        List<SysDept> deptList = list();
        List<TreeNode<Long>> nodes = deptList.stream().map(this::mapToTreeNode).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L, getTreeNodeConfig(), new DefaultNodeParser<>());
    }


    /**
     * 查询TreeNodeConfig
     *
     * @return treeNodeConfig
     */
    private TreeNodeConfig getTreeNodeConfig() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setIdKey("deptId");
        treeNodeConfig.setNameKey("deptName");
        treeNodeConfig.setParentIdKey("parentId");
        treeNodeConfig.setDeep(15);
        return treeNodeConfig;
    }

    /**
     * 转换
     *
     * @param dept
     * @return
     */
    private TreeNode<Long> mapToTreeNode(SysDept dept) {
        TreeNode<Long> treeNode = new TreeNode<>(dept.getDeptId(), dept.getParentId(), dept.getDeptName(), dept.getOrderNum());
        treeNode.setExtra(getMap(dept));
        return treeNode;
    }


    /**
     * 获取其他属性
     *
     * @param obj obj
     * @return map
     */
    public Map<String, Object> getMap(Object obj) {

        Map<String, Object> map = BeanUtil.beanToMap(obj, false, true);

        map.remove("deptName");
        map.remove("deptId");
        map.remove("parentId");
        map.remove("orderNum");
        return map;
    }


    /**
     * 新增部门检查
     *
     * @param addDTO 新增部门传输对象
     */
    private void checkAdd(DeptAddDTO addDTO) {

        if (ObjectUtil.isNotNull(addDTO.getParentId()) && addDTO.getParentId() != 0L) {
            checkParent(addDTO.getParentId());
        }else {
            // 设置默认值
            addDTO.setParentId(0L);
        }


        SysDept sysDept = getBaseMapper().selectByParentIdAndDeptName(addDTO.getParentId(), addDTO.getDeptName());
        if (ObjectUtil.isNotNull(sysDept)) {
            ReportUtil.throwEx(StrUtil.format("部门[{}]已经存在!", addDTO.getDeptName()));
        }
    }


    /**
     * 检查修改部门方法
     *
     * @param editDTO 编辑传输对象
     */
    private void checkEdit(DeptEditDTO editDTO) {

        // 部门自己不能挂在自己下
        if (editDTO.getDeptId().equals(editDTO.getParentId())) {
            ReportUtil.throwEx(StrUtil.format("父部门ID[{}]不合法!", editDTO.getParentId()));
        }

        SysDept sysDept = getById(editDTO.getDeptId());

        if (ObjectUtil.isNull(sysDept)) {
            ReportUtil.throwEx(StrUtil.format("部门ID[{}]不存在!", editDTO.getDeptId()));
        }

        if (ObjectUtil.isNull(editDTO.getParentId())) {
            editDTO.setParentId(0L);
        }

        if (sysDept.getParentId().equals(editDTO.getParentId()) && sysDept.getDeptName().equals(editDTO.getDeptName())) {
            return;
        }

        if (editDTO.getParentId() != 0L) {
            checkParent(editDTO.getParentId());
        }

        sysDept = getBaseMapper().selectByParentIdAndDeptName(editDTO.getParentId(), editDTO.getDeptName());

        if (ObjectUtil.isNotNull(sysDept)) {
            ReportUtil.throwEx(StrUtil.format("部门[{}]已存在!", editDTO.getDeptName()));
        }
    }

    /**
     * 检查父部门信息
     *
     * @param parentId 父部门ID
     */
    private void checkParent(Long parentId) {
        SysDept sysDept = getBaseMapper().selectById(parentId);
        if (ObjectUtil.isNull(sysDept)) {
            ReportUtil.throwEx(StrUtil.format("父部门ID[{}]不存在!", parentId));
        }
        if (sysDept.getStatus()) {
            ReportUtil.throwEx(StrUtil.format("父部门[{}]已经禁用!", parentId));
        }
    }

}
