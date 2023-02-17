package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限修改传输对象
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "权限分页查询传输对象", description = "权限分页查询传输对象")
public class PermissionPageDTO extends PageDTO {

    @ApiModelProperty(value = "关键字模糊查询(权限名称 | 权限编码 | 接口地址 | 描叙)")
    private String key;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

}
