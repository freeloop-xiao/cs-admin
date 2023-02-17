package com.cs.admin.system.log.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author free loop
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysLog对象", description = "系统日志")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "日志状态 0:正常 1:异常")
    private Integer logStatus;

    @ApiModelProperty(value = "描叙")
    private String description;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求路径")
    private String path;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "请求ip")
    private String requestIp;

    @ApiModelProperty(value = "请求耗时")
    private Long time;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "异常详情")
    private String exceptionDetail;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }


}
