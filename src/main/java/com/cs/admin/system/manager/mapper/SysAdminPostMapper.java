package com.cs.admin.system.manager.mapper;

import com.cs.admin.system.manager.domain.entity.SysAdminPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Mapper
public interface SysAdminPostMapper extends BaseMapper<SysAdminPost> {

    /**
     * 伤处用户岗位信息
     * @param userId 用户ID
     * @return int
     */
    Integer deleteByUserId(@Param("userId") Long userId);

    /**
     * 查询用户岗位
     * @param userId 用户ID
     * @return adminPost
     */
    SysAdminPost selectByUserId(@Param("userId") Long userId);
}
