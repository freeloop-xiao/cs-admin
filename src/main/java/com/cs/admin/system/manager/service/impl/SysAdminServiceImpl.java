package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.TokenUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysAdminConvert;
import com.cs.admin.system.manager.domain.covert.SysDeptConvert;
import com.cs.admin.system.manager.domain.dto.*;
import com.cs.admin.system.manager.domain.entity.*;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import com.cs.admin.system.manager.domain.vo.SubDeptVO;
import com.cs.admin.system.manager.mapper.SysAdminMapper;
import com.cs.admin.system.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统管理员表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Autowired
    private SysDeptConvert sysDeptConvert;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysAdminConvert sysAdminConvert;

    @Autowired
    private SysAdminRoleService sysAdminRoleService;

    @Autowired
    private SysAdminPostService sysAdminPostService;

    @Autowired
    private SysAdminDeptService sysAdminDeptService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(AdminAddDTO addDTO) {

        // 前置检查
        checkAdd(addDTO);

        SysAdmin sysAdmin = sysAdminConvert.toEntity(addDTO);

        // 如果未传密码设置默认密码
        if (ObjectUtil.isNull(sysAdmin.getPassword())) {
            sysAdmin.setPassword(SecureUtil.md5(TokenUtil.DEFAULT_PASSWORD));
        } else {
            sysAdmin.setPassword(passwordEncoder.encode(SecureUtil.md5(sysAdmin.getPassword())));
        }

        // 保存用户基本信息
        save(sysAdmin);

        // 保存用户角色
        List<SysAdminRole> adminRoles = addDTO.getRoleIds().stream().map(x -> new SysAdminRole(sysAdmin.getUserId(), x)).collect(Collectors.toList());

        if (adminRoles.size() > 0) {
            sysAdminRoleService.saveBatch(adminRoles);
        }

        //保存用户岗位
        if (ObjectUtil.isNotNull(addDTO.getPostId())) {
            sysAdminPostService.save(new SysAdminPost(sysAdmin.getUserId(), addDTO.getPostId()));
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long userId) {
        sysAdminRoleService.removeByUserId(userId);
        sysAdminPostService.removeByUserId(userId);
        sysAdminDeptService.removeByUserId(userId);
        return getBaseMapper().deleteByUserId(userId, WebUtil.getUserId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean edit(AdminEditDTO editDTO) {

        // 前置修改前校验
        checkEdit(editDTO);

        // 岗位处理
        editPost(editDTO.getUserId(), editDTO.getPostId());

        // 部门处理
        editDept(editDTO.getUserId(), editDTO.getDeptId());

        // 处理角色列表
        editRoles(editDTO.getUserId(), editDTO.getRoleIds());

        // 修改用户信息
        SysAdmin sysAdmin = sysAdminConvert.toEntity(editDTO);
        sysAdmin.setUpdateBy(WebUtil.getUserId());

        return updateById(sysAdmin);
    }

    @Override
    public Boolean reSetPwd(AdminEditPwdDTO editPwdDTO) {
        SysAdmin admin = getById(WebUtil.getUserId());
        return resetPwd(admin, editPwdDTO);
    }

    @Override
    public Boolean reSetOtherPwd(AdminEditPwdDTO editPwdDTO) {
        SysAdmin admin = getById(editPwdDTO.getPwdUserId());
        return resetPwd(admin, editPwdDTO);
    }

    @Override
    public AdminVO getMyVO(Long userId) {
        return sysAdminConvert.toVO(getBaseMapper().selectAdminInfoByUserId(userId));
    }

    @Override
    public AdminVO getVO(Long userId) {
        AdminVO adminVO = sysAdminConvert.toVO(getById(userId));
        adminVO.setRoleList(sysRoleService.getSubRoleByUserId(userId));
        SysAdminDept adminDept = sysAdminDeptService.getByUserId(userId);

        if (ObjectUtil.isNull(adminDept)){
            return adminVO;
        }

        SysDept dept = sysDeptService.getById(adminDept.getDeptId());
        if (ObjectUtil.isNull(dept)){
            return adminVO;
        }
        List<SubDeptVO> list = new ArrayList<>();
        list.add(sysDeptConvert.toSubVO(dept));
        adminVO.setDeptList(list);
        return adminVO;
    }

    @Override
    public PageVO<AdminVO> page(AdminPageDTO pageDTO) {
        Page<SysAdmin> result = getBaseMapper().selectAdminPage(PageUtil.page(pageDTO),
                    pageDTO.getDeptId(),
                    pageDTO.getSex(),
                    pageDTO.getIsLocked(),
                    pageDTO.getStartTime(),
                    pageDTO.getEndTime(),pageDTO.getKey());
        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysAdminConvert.toVO(result.getRecords()));
    }


    @Override
    public AdminDTO getAdminDtoByAccount(String account) {
        return getBaseMapper().selectAdminInfoByAccount(account);
    }



    /**
     * 检查新增管理员DTO
     *
     * @param addDTO dto
     */
    private void checkAdd(AdminAddDTO addDTO) {

        // 检查账户是否存在
        checkAccount(addDTO.getAccount());

        // 检查部门
        checkDept(addDTO.getDeptId());

        // 检查岗位
        checkPost(addDTO.getPostId());

        // 检查角色列表
        checkRoleIds(addDTO.getRoleIds());

    }

    /**
     * 检查账户
     *
     * @param account 账户
     */
    private void checkAccount(String account) {
        // 检查管理员账号是否存在
        SysAdmin sysAdmin = getBaseMapper().selectByAccount(account);
        if (ObjectUtil.isNotNull(sysAdmin)) {
            ReportUtil.throwEx(StrUtil.format("账号[{}]已经存在!", account));
        }
    }

    /**
     * 检查部门
     *
     * @param deptId 部门ID
     */
    private void checkDept(Long deptId) {

        if (ObjectUtil.isNull(deptId)) {
            return;
        }

        SysDept sysDept = sysDeptService.getById(deptId);
        if (ObjectUtil.isNull(sysDept)) {
            ReportUtil.throwEx(StrUtil.format("部门ID[{}]不存在!", deptId));
        }
    }

    /**
     * 检查岗位
     *
     * @param postId 岗位ID
     */
    private void checkPost(Long postId) {

        if (ObjectUtil.isNull(postId)) {
            return;
        }

        SysPost sysPost = sysPostService.getById(postId);
        if (ObjectUtil.isNull(sysPost)) {
            ReportUtil.throwEx(StrUtil.format("岗位ID[{}]不存在!", postId));
        }
    }


    /**
     * 检查角色列表
     *
     * @param roleIds 角色列表
     */
    private void checkRoleIds(Set<Long> roleIds) {

        if (ObjectUtil.isNull(roleIds) || roleIds.size() == 0) {
            return;
        }

        List<SysRole> roles = sysRoleService.getByRoleIds(roleIds);
        Set<Long> roleIdSet = roles.stream().map(SysRole::getRoleId).collect(Collectors.toSet());

        for (Long roleId : roleIds) {
            if (!roleIdSet.contains(roleId)) {
                ReportUtil.throwEx(StrUtil.format("角色ID[{}]不存在!", roleId));
            }
        }
    }

    /**
     * 修改用户信息前置检查
     *
     * @param editDTO dto
     */
    private void checkEdit(AdminEditDTO editDTO) {

        SysAdmin sysAdmin = getById(editDTO.getUserId());

        // 检查用户是否存在
        if (sysAdmin.getDelFlag()) {
            ReportUtil.throwEx(StrUtil.format("用户ID[{}]不存在!", editDTO.getUserId()));
        }

        // 检查用户账户是否修改
        if (!sysAdmin.getAccount().equals(editDTO.getAccount())) {
            checkAccount(editDTO.getAccount());
        }
    }


    /**
     * 修改用户角色
     *
     * @param userId  用户ID
     * @param roleIds 角色列表
     */
    private void editRoles(Long userId, Set<Long> roleIds) {

        if (ObjectUtil.isNull(roleIds) || roleIds.size() == 0) {
            sysAdminRoleService.removeByUserId(userId);
            return;
        }

        List<SysAdminRole> roles = sysAdminRoleService.getByUserId(userId);

        Set<Long> existRoleIds = roles.stream().map(SysAdminRole::getRoleId).collect(Collectors.toSet());

        // 删除角色
        Set<Long> delRoleIds = new HashSet<>(existRoleIds);
        delRoleIds.removeAll(roleIds);

        if (delRoleIds.size() > 0) {
            sysAdminRoleService.removeAdminRoles(userId, delRoleIds);
        }

        // 新增角色
        roleIds.removeAll(existRoleIds);

        checkRoleIds(roleIds);

        if (roleIds.size() > 0) {
            sysAdminRoleService.saveBatch(roleIds.stream().map(x -> new SysAdminRole(userId, x)).collect(Collectors.toList()));
        }
    }

    /**
     * 修改用户岗位
     *
     * @param userId 用户ID
     * @param postId 岗位ID
     */
    private void editPost(Long userId, Long postId) {

        if (ObjectUtil.isNull(postId)) {
            return;
        }

        SysAdminPost sysAdminPost = sysAdminPostService.getByUserId(userId);

        // 用户是否存在岗位信息
        if (ObjectUtil.isNotNull(sysAdminPost)) {

            // 如果修改岗位需要检查
            if (!postId.equals(sysAdminPost.getPostId())) {
                checkPost(postId);
                sysAdminPostService.removeByUserId(userId);
                sysAdminPostService.save(new SysAdminPost(userId, postId));
            }

        } else {
            checkPost(postId);
            sysAdminPostService.save(new SysAdminPost(userId, postId));
        }
    }


    private void editDept(Long userId, Long deptId) {

        if (ObjectUtil.isNull(deptId)) {
            return;
        }

        SysAdminDept sysAdminDept = sysAdminDeptService.getByUserId(userId);

        // 用户是否存在部门信息
        if (ObjectUtil.isNotNull(sysAdminDept)) {

            // 如果修改部门信息
            if (!deptId.equals(sysAdminDept.getDeptId())) {
                checkDept(deptId);
                sysAdminDeptService.removeByUserId(userId);
                sysAdminDeptService.save(new SysAdminDept(userId, deptId));
            }

        } else {

            // 如果不存在部门信息
            checkDept(deptId);
            sysAdminDeptService.save(new SysAdminDept(userId, deptId));
        }
    }


    private Boolean resetPwd(SysAdmin admin, AdminEditPwdDTO editPwdDTO) {

        if (ObjectUtil.isNull(admin) || admin.getDelFlag()) {
            ReportUtil.throwEx(StrUtil.format("用户ID[{}]不存在!", WebUtil.getUserId()));
        }

        if (!editPwdDTO.getNewPwd().equals(editPwdDTO.getReNewPwd())) {
            ReportUtil.throwEx("新密码两次不一致!");
        }

        String newPwd = passwordEncoder.encode(SecureUtil.md5(editPwdDTO.getNewPwd()));

        return getBaseMapper().updatePwdByUserId(WebUtil.getUserId(), newPwd, LocalDateTime.now());
    }

}
