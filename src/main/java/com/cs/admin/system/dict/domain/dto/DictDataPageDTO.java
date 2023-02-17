package com.cs.admin.system.dict.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典数据分页查询DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "字典数据分页查询DTO", description = "字典数据分页查询DTO")
public class DictDataPageDTO extends PageDTO {

    @ApiModelProperty(value = "关键字查找( 字典标签 | 字典键值)")
    private String key;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "是否默认（0否 1是）")
    private Boolean isDefault;

    @ApiModelProperty(value = "父字典数据ID")
    private Long parentDataId;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

}
