package com.cs.admin.system.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.dto.PostPageDTO;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.vo.PostVO;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
public interface SysPostService extends IService<SysPost> {


    /**
     * 新增岗位
     *
     * @param addDTO 新增DTO
     * @return true/false
     */
    Boolean add(PostAddDTO addDTO);

    /**
     * 删除岗位
     *
     * @param postId 岗位ID
     * @return true/false
     */
    Boolean del(Long postId);

    /**
     * 编辑岗位
     *
     * @param editDTO 编辑岗位DTO
     * @return true/false
     */
    Boolean edit(PostEditDTO editDTO);

    /**
     * 获取岗位VO
     *
     * @param postId 岗位ID
     * @return postVO
     */
    PostVO getVO(Long postId);

    /**
     * 分页查询传输对象
     *
     * @param pageDTO pageDTO
     * @return pageVO
     */
    PageVO<PostVO> page(PostPageDTO pageDTO);

    /**
     * 查询用户岗位
     *
     * @param userId 用户ID
     * @return post
     */
    SysPost getByUserId(Long userId);

}
