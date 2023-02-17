package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysPermissionConvert;
import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionEditDTO;
import com.cs.admin.system.manager.domain.dto.PermissionPageDTO;
import com.cs.admin.system.manager.domain.entity.SysPermission;
import com.cs.admin.system.manager.domain.vo.PermissionVO;
import com.cs.admin.system.manager.mapper.SysPermissionMapper;
import com.cs.admin.system.manager.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限编码信息表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionConvert sysPermissionConvert;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(PermissionAddDTO addDTO) {
        checkAdd(addDTO);
        return save(sysPermissionConvert.toEntity(addDTO));
    }


    @Override
    public Boolean del(Long permissionId) {
        return removeById(permissionId);
    }

    @Override
    public Boolean edit(PermissionEditDTO editDTO) {

        // 检查修改是否符合条件
        checkEdit(editDTO);

        SysPermission sysPermission = sysPermissionConvert.toEntity(editDTO);
        sysPermission.setUpdateBy(WebUtil.getUserId());

        return updateById(sysPermission);
    }

    @Override
    public PermissionVO getVO(Long permissionId) {
        return sysPermissionConvert.toVO(getById(permissionId));
    }

    @Override
    public PageVO<PermissionVO> page(PermissionPageDTO pageDTO) {

        Page<SysPermission> pageCondition = PageUtil.page(pageDTO);
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();

        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        // 权限关键字模糊查询(编码、url、描叙)
        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("name", pageDTO.getKey())
                    .or().like("code", pageDTO.getKey())
                    .or().like("url", pageDTO.getKey())
                    .or().like("description", pageDTO.getKey())
            );
        }

        Page<SysPermission> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysPermissionConvert.toVO(result.getRecords()));
    }


    @Override
    public List<PermissionVO> list(String key) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(key)) {
            queryWrapper.or().like("name", key);
            queryWrapper.or().like("code", key);
            queryWrapper.or().like("url", key);
            queryWrapper.or().like("description", key);
        }

        return sysPermissionConvert.toVO(list(queryWrapper));
    }


    @Override
    public List<SysPermission> list(Set<Long> permissionIds) {
        if (permissionIds == null || permissionIds.size() == 0){
            return new ArrayList<>();
        }
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("permission_id",permissionIds);
        return list(queryWrapper);
    }

    /**
     * 检查参数增加权限是否符合条件
     *
     * @param addDTO addDTO
     */
    private void checkAdd(PermissionAddDTO addDTO) {
        // 检查权限编码是否存在
        checkCode(addDTO.getCode());
        // 检查权限名称是否存在
        checkName(addDTO.getName());
    }


    /**
     * 检查修改权限参数是否符合条件
     *
     * @param editDTO 编辑DTO
     */
    private void checkEdit(PermissionEditDTO editDTO) {

        SysPermission sysPermission = getById(editDTO.getPermissionId());
        if (ObjectUtil.isNull(sysPermission)) {
            ReportUtil.throwEx(StrUtil.format("权限ID[{}]不存在!", editDTO.getPermissionId()));
        }

        // 如果修改code,检查
        if (!editDTO.getCode().equals(sysPermission.getCode())) {
            checkCode(editDTO.getCode());
        }

        // 如果修改name,检查
        if (!editDTO.getName().equals(sysPermission.getName())) {
            checkName(editDTO.getName());
        }
    }

    /**
     * 检查code是否已经存在
     *
     * @param code code
     */
    private void checkCode(String code) {
        SysPermission permission = getBaseMapper().selectByCode(code);
        if (ObjectUtil.isNotNull(permission)) {
            ReportUtil.throwEx(StrUtil.format("权限code[{}]已经存在!", code));
        }
    }

    /**
     * 检查权限名称是否存有
     *
     * @param name name
     */
    private void checkName(String name) {
        SysPermission permission = getBaseMapper().selectByName(name);
        if (ObjectUtil.isNotNull(permission)) {
            ReportUtil.throwEx(StrUtil.format("权限name[{}]已经存在!", name));
        }
    }


}
