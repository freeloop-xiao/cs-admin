package com.cs.admin.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 20:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "令牌对象", description = "令牌对象")
public class TokenVO {

    @ApiModelProperty(value = "访问token")
    private String accessToken;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "accessToken过期时间")
    private Long expires;

    public TokenVO() {
    }

    public TokenVO(String accessToken, String refreshToken, Long expires) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expires = expires;
    }

}
