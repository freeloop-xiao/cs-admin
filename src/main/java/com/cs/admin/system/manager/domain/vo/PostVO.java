package com.cs.admin.system.manager.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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
public class PostVO {

    @ApiModelProperty(value = "岗位ID")
    private Long postId;

    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "显示顺序")
    private Integer postSort;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
