package com.cs.admin.system.manager.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户与部门关联表
 * </p>
 *
 * @author free loop
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysAdminDept对象", description = "用户与部门关联表")
public class SysAdminDept extends Model<SysAdminDept> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    public SysAdminDept() {
    }

    public SysAdminDept(Long userId, Long deptId) {
        this.userId = userId;
        this.deptId = deptId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
