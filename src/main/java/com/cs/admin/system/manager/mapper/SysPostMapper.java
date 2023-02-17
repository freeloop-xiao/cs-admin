package com.cs.admin.system.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.manager.domain.entity.SysPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    /**
     * 通过岗位编码查询岗位
     *
     * @param postCode 岗位编码
     * @return post
     */
    SysPost selectByPostCode(@Param("postCode") String postCode);


    /**
     * 通过岗位名称查询
     *
     * @param postName 岗位名称
     * @return post
     */
    SysPost selectByPostName(@Param("postName") String postName);


    /**
     * 查询用户岗位信息
     *
     * @param userId 用户ID
     * @return post
     */
    SysPost selectByUserId(@Param("userId") Long userId);

}
