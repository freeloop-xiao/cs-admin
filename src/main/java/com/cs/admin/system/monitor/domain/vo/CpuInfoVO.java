package com.cs.admin.system.monitor.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * CPU信息VO
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CPU信息VO", description = "CPU信息VO")
public class CpuInfoVO {

    @ApiModelProperty(value = "CPU名称")
    private String name;

    @ApiModelProperty(value = "CPU个数")
    private String packages;

    @ApiModelProperty(value = "核心数")
    private String core;

    @ApiModelProperty(value = "核个数")
    private Integer coreNumber;

    @ApiModelProperty(value = "逻辑CPU个数")
    private String logic;

    @ApiModelProperty(value = "使用率")
    private String used;

    @ApiModelProperty(value = "空闲率")
    private String available;
}
