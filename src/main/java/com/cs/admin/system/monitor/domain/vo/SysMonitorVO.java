package com.cs.admin.system.monitor.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统监控VO
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统监控VO", description = "系统监控VO")
public class SysMonitorVO {

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "项目运行时间")
    private String day;

    @ApiModelProperty(value = "服务器ip")
    private String ip;

    @ApiModelProperty(value = "cpu信息")
    private CpuInfoVO cpuInfo;

    @ApiModelProperty(value = "内存信息")
    private MemoryInfoVO memoryInfo;

    @ApiModelProperty(value = "交换分区")
    private SwapInfoVO swapInfo;

    @ApiModelProperty(value = "磁盘信息")
    private DiskInfoVO diskInfo;


}
