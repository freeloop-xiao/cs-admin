package com.cs.admin.system.dict.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "字典类型修改DTO", description = "字典类型修改DTO")
public class DictTypeEditDTO {

    @NotNull(message = "字典ID未传!")
    @ApiModelProperty(value = "字典类型ID")
    private Long dictId;

    @NotEmpty(message = "字典名称未传!")
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @NotEmpty(message = "字典编码未传!")
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
