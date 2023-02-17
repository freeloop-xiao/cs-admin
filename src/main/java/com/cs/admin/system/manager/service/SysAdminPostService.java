package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.system.manager.domain.entity.SysAdminPost;

/**
 * <p>
 * 用户与岗位关联表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
public interface SysAdminPostService extends IService<SysAdminPost> {


    /**
     * 统计岗位ID用户数
     *
     * @param postId 岗位ID
     * @return int
     */
    Integer countByPostId(Long postId);

    /**
     * 删除用户岗位管理信息
     *
     * @param userId 岗位ID
     */
    void removeByUserId(Long userId);


    /**
     * 查询用户岗位
     * @param userId 用户ID
     * @return post
     */
    SysAdminPost getByUserId(Long userId);


}
