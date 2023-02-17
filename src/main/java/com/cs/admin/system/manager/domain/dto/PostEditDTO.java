package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "岗位编辑传输对象", description = "岗位编辑传输对象")
public class PostEditDTO {

    @NotNull(message = "岗位ID未传!")
    @ApiModelProperty(value = "岗位ID")
    private Long postId;

    @NotEmpty(message = "岗位编码未传!")
    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @NotEmpty(message = "岗位名称未传!")
    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "显示顺序")
    private Integer postSort;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
