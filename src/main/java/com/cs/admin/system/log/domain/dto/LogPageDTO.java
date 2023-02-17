package com.cs.admin.system.log.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/16 14:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页日志查询传输对象", description = "分页日志查询传输对象")
public class LogPageDTO extends PageDTO {

    @ApiModelProperty(value = "日志类型(暂不使用)")
    private String logType;

    @ApiModelProperty(value = "日志状态(0:请求成功 1:请求失败)")
    private Integer logStatus;

    @ApiModelProperty(value = "日志描叙")
    private String description;

}
