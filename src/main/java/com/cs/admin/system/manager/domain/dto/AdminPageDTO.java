package com.cs.admin.system.manager.domain.dto;

import com.cs.admin.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 分页查询管理员DTO
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页查询管理员DTO", description = "分页查询管理员DTO")
public class AdminPageDTO extends PageDTO {

    @ApiModelProperty(value = "关键字(账号 | 手机号 | 邮箱 | 用户名称 | 用户昵称)")
    private String key;

    @ApiModelProperty(value = "性别 0:女  1:男  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "是否锁定 0开放 1锁定")
    private Boolean isLocked;

    @ApiModelProperty(value = "注册开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "注册结束时间")
    private LocalDateTime endTime;
}
