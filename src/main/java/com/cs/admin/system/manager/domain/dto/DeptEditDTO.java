package com.cs.admin.system.manager.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门编辑传输对象DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "部门编辑传输对象DTO", description = "部门编辑传输对象DTO")
public class DeptEditDTO {

    @NotNull(message = "部门ID未传!")
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @NotEmpty(message = "部门名称未传!")
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "负责人")
    private Long leader;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "父部门id")
    private Long parentId;

}
