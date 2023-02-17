package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 权限编码信息表
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "增加权限列表DTO", description = "增加权限列表DTO")
public class PermissionAddDTO {

    @NotEmpty(message = "权限名称未传!")
    @ApiModelProperty(value = "接口权限名称")
    private String name;

    @NotEmpty(message = "权限编码未传!")
    @ApiModelProperty(value = "权限编码")
    private String code;

    @ApiModelProperty(value = "接口地址 格式 method:url")
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

    public PermissionAddDTO(){}

    public PermissionAddDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

}
