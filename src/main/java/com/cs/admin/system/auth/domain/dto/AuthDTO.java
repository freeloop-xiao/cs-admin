package com.cs.admin.system.auth.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "登陆认证传输对象", description = "登陆认证传输对象")
public class AuthDTO {

    @NotEmpty(message = "账号未传!")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotEmpty(message = "密码未传!")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotEmpty(message = "验证码流水未传!")
    @ApiModelProperty(value = "验证码流水")
    private String serNo;

    @NotEmpty(message = "验证码未传!")
    @ApiModelProperty(value = "验证码")
    private String code;


}
