package com.cs.admin.system.auth.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页查询传输对象", description = "分页查询传输对象")
public class OnlinePageDTO {

    @ApiModelProperty(value = "关键字查询")
    private String key;

    @ApiModelProperty(value = "页码(默认从1开始)")
    private Long page;

    @ApiModelProperty(value = "页记录(默认10)条数")
    private Long limit;

    public Long getPage() {
        if (page == null) {
            return 0L;
        }
        return page;
    }

    public Long getLimit() {
        if (limit == null) {
            return 10L;
        }
        return limit;
    }

}
