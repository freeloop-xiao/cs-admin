package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色分页查询传输对象
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "角色分页查询传输对象", description = "角色分页查询传输对象")
public class RolePageDTO extends PageDTO {

    @ApiModelProperty(value = "角色编码|角色名称|描述")
    private String key;

    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private Boolean status;

}
