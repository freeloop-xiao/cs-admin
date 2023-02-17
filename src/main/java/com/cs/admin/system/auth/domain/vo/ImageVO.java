package com.cs.admin.system.auth.domain.vo;

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
@ApiModel(value = "图形验证码VO", description = "图形验证码VO")
public class ImageVO {

    @ApiModelProperty(value = "验证码流水")
    private String serNo;

    @ApiModelProperty(value = "图片流(Base64)")
    private String image;

    @ApiModelProperty(value = "图片结果")
    private String code;

    public ImageVO(String serNo, String image) {
        this.serNo = serNo;
        this.image = image;
    }


}
