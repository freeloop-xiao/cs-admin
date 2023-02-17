package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统菜单分页查询
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统分页查询菜单传输对象", description = "系统分页查询菜单传输对象")
public class MenuPageDTO extends PageDTO {

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentMenuId;

    @ApiModelProperty(value = "菜单类型")
    private Integer type;

    @ApiModelProperty(value = "关键字(菜单标题 | 组件名称 | 组件 | 链接地址 |权限)")
    private String key;

    @ApiModelProperty(value = "是否外链")
    private Boolean isFrame;

    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Boolean status;

}
