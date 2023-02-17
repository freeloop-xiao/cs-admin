package com.cs.admin.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 11:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页请求公共类", description = "分页请求公共类")
public class PageDTO {

    @ApiModelProperty(value = "页码(默认从1开始)")
    private Long page;

    @ApiModelProperty(value = "页记录(默认10)条数")
    private Long limit;

    @ApiModelProperty(value = "排序字段(降序)")
    private String orderDescBy;

    @ApiModelProperty(value = "排序字段(升序)")
    private String orderAscBy;

}
