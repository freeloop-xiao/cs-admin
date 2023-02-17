package com.cs.admin.system.manager.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysAdminRole对象", description = "用户角色关联表")
public class SysAdminRole extends Model<SysAdminRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }


    public SysAdminRole() {
    }

    public SysAdminRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
