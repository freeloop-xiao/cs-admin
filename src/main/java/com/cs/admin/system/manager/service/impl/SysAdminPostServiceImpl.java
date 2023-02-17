package com.cs.admin.system.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.system.manager.domain.entity.SysAdminPost;
import com.cs.admin.system.manager.mapper.SysAdminPostMapper;
import com.cs.admin.system.manager.service.SysAdminPostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Service
public class SysAdminPostServiceImpl extends ServiceImpl<SysAdminPostMapper, SysAdminPost> implements SysAdminPostService {

    @Override
    public Integer countByPostId(Long postId) {
        QueryWrapper<SysAdminPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        return count(queryWrapper);
    }

    @Override
    public void removeByUserId(Long userId) {
        getBaseMapper().deleteByUserId(userId);
    }


    @Override
    public SysAdminPost getByUserId(Long userId) {
        return getBaseMapper().selectByUserId(userId);
    }
}
