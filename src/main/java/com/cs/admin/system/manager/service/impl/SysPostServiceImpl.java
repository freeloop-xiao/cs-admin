package com.cs.admin.system.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysPostConvert;
import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.dto.PostPageDTO;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.vo.PostVO;
import com.cs.admin.system.manager.mapper.SysPostMapper;
import com.cs.admin.system.manager.service.SysAdminPostService;
import com.cs.admin.system.manager.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    @Autowired
    private SysPostConvert sysPostConvert;

    @Autowired
    private SysAdminPostService sysAdminPostService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(PostAddDTO addDTO) {
        checkAdd(addDTO);
        return save(sysPostConvert.toEntity(addDTO));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long postId) {
        if (sysAdminPostService.countByPostId(postId) > 0) {
            ReportUtil.throwEx(StrUtil.format("岗位[{}]已关联用户,不能删除(如需删除请先解除关联)!"));
        }
        return removeById(postId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean edit(PostEditDTO editDTO) {

        // 检查修改是否符合条件
        checkEdit(editDTO);

        SysPost post = sysPostConvert.toEntity(editDTO);
        post.setUpdateBy(WebUtil.getUserId());

        return updateById(post);
    }


    @Override
    public PostVO getVO(Long postId) {
        return sysPostConvert.toVO(getById(postId));
    }

    @Override
    public PageVO<PostVO> page(PostPageDTO pageDTO) {

        Page<SysPost> pageCondition = PageUtil.page(pageDTO);
        QueryWrapper<SysPost> queryWrapper = new QueryWrapper<>();

        // 是否有状态条件
        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        // 关键字部门岗位编码、岗位名称模糊查询
        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("post_code", pageDTO.getKey()).or().like("post_name", pageDTO.getKey()));
        }

        Page<SysPost> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysPostConvert.toVO(result.getRecords()));
    }


    @Override
    public SysPost getByUserId(Long userId) {
        return getBaseMapper().selectByUserId(userId);
    }

    /**
     * 检查新增岗位传输对象
     *
     * @param addDTO 传输对象
     */
    private void checkAdd(PostAddDTO addDTO) {
        checkPostCode(addDTO.getPostCode());
        checkPostName(addDTO.getPostName());
    }


    private void checkEdit(PostEditDTO editDTO) {

        SysPost sysPost = getById(editDTO.getPostId());

        if (ObjectUtil.isNull(sysPost)) {
            ReportUtil.throwEx(StrUtil.format("岗位ID[{}]不存在!", editDTO.getPostId()));
        }

        if (!editDTO.getPostCode().equals(sysPost.getPostCode())) {
            checkPostCode(editDTO.getPostCode());
        }

        if (!editDTO.getPostName().equals(sysPost.getPostName())) {
            checkPostName(editDTO.getPostName());
        }

    }

    /**
     * 检查postCode
     *
     * @param postCode postCOde
     */
    private void checkPostCode(String postCode) {

        SysPost post = getBaseMapper().selectByPostCode(postCode);

        if (ObjectUtil.isNotNull(post)) {
            ReportUtil.throwEx(StrUtil.format("岗位编码[{}]已经存在!", postCode));
        }

    }

    /**
     * 检查postName
     *
     * @param postName postName
     */
    private void checkPostName(String postName) {

        SysPost post = getBaseMapper().selectByPostName(postName);

        if (ObjectUtil.isNotNull(post)) {
            ReportUtil.throwEx(StrUtil.format("岗位名称[{}]已经存在!", postName));
        }
    }


}
