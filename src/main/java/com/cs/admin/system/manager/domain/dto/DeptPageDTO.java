package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门增加DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "部门分页查询DTO", description = "部门分页查询DTO")
public class DeptPageDTO extends PageDTO {

    @ApiModelProperty(value = "父部门id")
    private Long parentId;

    @ApiModelProperty(value = "部门名称 | 联系电话 | 邮箱 模糊查找")
    private String key;

    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private Boolean status;

}
