package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 18:16
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户查询综合信息", description = "用户查询综合信息")
public class AdminDTO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

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

    @ApiModelProperty(value = "是否锁定 0开放 1锁定")
    private Boolean isLocked;

    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "用户注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "部门名称")
    private List<SysDept> deptList;

    @ApiModelProperty(value = "岗位名称")
    private List<SysPost> postList;

    @ApiModelProperty(value = "角色列表")
    private List<SysRole> roleList;

    @ApiModelProperty(value = "权限列表")
    private Set<String> permissions;


}
