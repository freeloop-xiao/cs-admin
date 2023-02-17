package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 岗位分页传输对象
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "岗位分页传输对象", description = "岗位分页传输对象")
public class PostPageDTO extends PageDTO {

    @ApiModelProperty(value = "岗位编码 | 岗位名称")
    private String key;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

}
