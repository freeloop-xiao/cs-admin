package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.entity.SysAdmin;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 系统管理员表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {


    /**
     * 查询用户基本信息
     *
     * @param account 账户
     * @return adminDTO
     */
    AdminDTO selectAdminInfoByAccount(@Param("account") String account);


    AdminDTO selectAdminInfoByUserId(@Param("userId") Long userId);


    /**
     * 查询用户部门信息
     *
     * @param userId 用户ID
     * @return dept
     */
    SysDept selectDeptByUserId(@Param("userId") Long userId);


    /**
     * 查询用户部门信息
     *
     * @param userId 用户ID
     * @return post
     */
    SysPost selectPostByUserId(@Param("userId") Long userId);

    /**
     * 查找用户角色
     *
     * @param userId 用户ID
     * @return role
     */
    SysRole selectRoleByUserId(@Param("userId") Long userId);

    /**
     * 查找菜单权限列表
     *
     * @param userId 用户ID
     * @return list
     */
    Set<String> selectMenuPermissionByUserId(Long userId);


    /**
     * 通过账号查询管理员
     *
     * @param account 账号
     * @return sysAdmin
     */
    SysAdmin selectByAccount(@Param("account") String account);


    /**
     * 删除用户
     *
     * @param userId   用户ID
     * @param updateBy 操作用户ID
     * @return true/false
     */
    Boolean deleteByUserId(@Param("userId") Long userId, @Param("updateBy") Long updateBy);

    /**
     * 修改用户密码
     *
     * @param userId 用户ID
     * @param pwd    pwd
     * @param pwdResetTime 密码修改时间
     * @return 1/0
     */
    Boolean updatePwdByUserId(@Param("userId") Long userId, @Param("pwd") String pwd,@Param("pwdResetTime") LocalDateTime pwdResetTime);


    /**
     * 分页查询用户信息
     * @param page 分页信息
     * @param deptId 部门Id
     * @param sex 性别
     * @param isLocked 是否锁定
     * @param startTime 开始时间
     * @param endTime 结束书简
     * @param keyWord 关键字
     * @return 结果
     */
    Page<SysAdmin> selectAdminPage(Page<?> page,
                                    @Param("deptId") Long deptId,
                                    @Param("sex") Integer sex,
                                    @Param("isLocked") Boolean isLocked,
                                    @Param("startTime") LocalDateTime startTime,
                                    @Param("endTime") LocalDateTime endTime,
                                    @Param("keyWord") String keyWord);

}
