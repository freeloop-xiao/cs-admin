package com.cs.admin.system.monitor.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 磁盘信息VO
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "磁盘信息VO", description = "磁盘信息VO")
public class DiskInfoVO {

    @ApiModelProperty(value = "磁盘总大小")
    private String total;

    @ApiModelProperty(value = "可用大小")
    private String available;

    @ApiModelProperty(value = "已使用")
    private String used;

    @ApiModelProperty(value = "使用率")
    private String usageRate;
}
