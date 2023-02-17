package com.cs.admin.system.auth.domain.vo;

import com.cs.admin.system.manager.domain.vo.AdminVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 22:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "登录认证VO", description = "登录认证VO")
public class AuthVO {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "用户信息")
    private AdminVO user;

    public AuthVO(AdminVO adminVO,String token){
        this.token = token;
        this.user = adminVO;
    }

}
