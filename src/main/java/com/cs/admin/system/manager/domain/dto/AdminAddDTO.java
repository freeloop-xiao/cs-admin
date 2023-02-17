package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

/**
 * <p>
 * 添加管理员用户DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "添加管理员用户DTO", description = "添加管理员用户DTO")
public class AdminAddDTO {

    @NotEmpty(message = "账号未传!")
    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "用户密码")
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

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "岗位id")
    private Long postId;

    @ApiModelProperty(value = "是否锁定 0开放 1锁定")
    private Boolean isLocked;

    @ApiModelProperty(value = "角色ID列表")
    private Set<Long> roleIds;
}
