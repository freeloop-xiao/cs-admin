package com.cs.admin.system.manager.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysAdminPost对象", description = "用户与岗位关联表")
public class SysAdminPost extends Model<SysAdminPost> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "岗位ID")
    private Long postId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }


    public SysAdminPost() {
    }

    public SysAdminPost(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

}
