package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统菜单增加传输对象DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统菜单增加传输对象DTO", description = "系统菜单增加传输对象DTO")
public class MenuAddDTO {

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentMenuId;

    @ApiModelProperty(value = "排序")
    private Integer menuSort;

    @NotNull(message = "菜单类型未传!")
    @ApiModelProperty(value = "菜单类型 0:目录 1:菜单 2:按钮")
    private Integer type;

    @NotBlank(message = "菜单标题未传!")
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String path;

    @ApiModelProperty(value = "是否外链")
    private Boolean isFrame;

    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
