package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.*;
import com.cs.admin.system.manager.domain.entity.SysAdmin;
import com.cs.admin.system.manager.domain.vo.AdminVO;

/**
 * <p>
 * 系统管理员表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
public interface SysAdminService extends IService<SysAdmin> {



    /**
     * 新增管理员用户
     *
     * @param addDTO 新增传输DTO
     * @return true/false
     */
    Boolean add(AdminAddDTO addDTO);

    /**
     * 删除管理员
     *
     * @param userId 用户ID
     * @return true/false
     */
    Boolean del(Long userId);

    /**
     * 修改管理员信息
     *
     * @param editDTO 修改管理员DTO
     * @return true/false
     */
    Boolean edit(AdminEditDTO editDTO);


    /**
     * 重置密码
     * @param editPwdDTO pwdDTO
     * @return true/false
     */
    Boolean reSetPwd(AdminEditPwdDTO editPwdDTO);

    /**
     * 修改用户密码
     * @param editPwdDTO pwdDTO
     * @return true/false
     */
    Boolean reSetOtherPwd(AdminEditPwdDTO editPwdDTO);


    /**
     * 查询当前用户信息
     * @param userId  用户ID
     * @return adminVO
     */
    AdminVO getMyVO(Long userId);

    /**
     * 获取管理员详情
     *
     * @param userId 用户ID
     * @return vo
     */
    AdminVO getVO(Long userId);

    /**
     * 分页查询管理员用户信息
     *
     * @param pageDTO 分页查询DTO
     * @return pageVO
     */
    PageVO<AdminVO> page(AdminPageDTO pageDTO);



    /**
     * 账户查询用户信息
     *
     * @param account 账户
     * @return adminDTO
     */
    AdminDTO getAdminDtoByAccount(String account);

}
