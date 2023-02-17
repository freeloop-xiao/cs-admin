package com.cs.admin.system.manager.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * PostVO对象
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PostVO对象", description = "PostVO对象")
public class SubPostVO {

    @ApiModelProperty(value = "岗位ID")
    private Long postId;

    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

}
