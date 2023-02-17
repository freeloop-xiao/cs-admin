package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * <p>
 * 角色修改传输对象DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "角色修改传输对象DTO", description = "角色修改传输对象DTO")
public class RoleEditDTO {

    @NotNull(message = "角色ID未传!")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @NotEmpty(message = "角色编码未传!")
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @NotEmpty(message = "角色名称未传!")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Size(max = 2000, message = "菜单列表个数超过范围")
    @ApiModelProperty(value = "菜单ID列表")
    private Set<Long> menuIds;


}
