package com.cs.admin.system.dict.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 修改字典数据DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "修改字典数据DTO", description = "修改字典数据DTO")
public class DictDataEditDTO {

    @NotNull(message = "字典数据ID未传!")
    @ApiModelProperty(value = "字典数据ID")
    private Long dataId;

    @NotEmpty(message = "字典编码未传!")
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典排序")
    private Integer dictSort;

    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    @NotEmpty(message = "字典键值未传!")
    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    @ApiModelProperty(value = "是否默认（0否 1是）")
    private Boolean isDefault;

    @ApiModelProperty(value = "父字典数据ID")
    private Long parentDataId;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
