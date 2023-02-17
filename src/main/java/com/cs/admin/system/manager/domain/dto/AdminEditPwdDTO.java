package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 修改当前登录用户密码传输对象DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "修改当前登录用户密码传输对象DTO", description = "修改当前登录用户密码传输对象DTO")
public class AdminEditPwdDTO {

    @ApiModelProperty(value = "用户ID")
    private Long pwdUserId;

    @NotBlank(message = "第一次新密码不能为空!")
    @ApiModelProperty(value = "第一次新密码")
    private String newPwd;

    @NotBlank(message = "第二次输入新密码不能为空!")
    @ApiModelProperty(value = "第二次输入新密码")
    private String reNewPwd;

}
