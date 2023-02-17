package com.cs.admin.system.manager.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统管理员VO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统管理员VO", description = "系统管理员VO")
public class AdminVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "性别 0:女  1:男  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "出身日期yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "部门信息")
    private List<SubDeptVO> deptList;

    @ApiModelProperty(value = "岗位信息")
    private List<SubPostVO> postList;

    @ApiModelProperty(value = "角色列表")
    private List<SubRoleVO> roleList;

    @ApiModelProperty(value = "是否锁定 0开放 1锁定")
    private Boolean isLocked;

    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "用户注册时间")
    private LocalDateTime createTime;

}
