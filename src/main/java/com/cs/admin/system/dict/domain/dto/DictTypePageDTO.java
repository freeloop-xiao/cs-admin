package com.cs.admin.system.dict.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value = "字典类型分页查询DTO", description = "字典类型分页查询DTO")
public class DictTypePageDTO extends PageDTO {

    @ApiModelProperty(value = "关键字模糊查询(字典编码 | 字典名称)")
    private String key;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

}
