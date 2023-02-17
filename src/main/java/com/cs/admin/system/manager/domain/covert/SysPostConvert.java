package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.common.mapstruct.BaseConvert;
import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.vo.PostVO;
import com.cs.admin.system.manager.domain.vo.SubPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/22 22:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPostConvert extends BaseConvert<PostAddDTO, SysPost, PostVO> {

    /**
     * 编辑DTO转换 sysPost
     *
     * @param dto 传输对象
     * @return sysPost
     */
    SysPost toEntity(PostEditDTO dto);

    /**
     * sysPost转换为subPostVO
     *
     * @param post sysPost
     * @return subPostVO
     */
    SubPostVO toSubVO(SysPost post);

    /**
     * sysPost列表转换为subPostVO
     *
     * @param postList postList
     * @return list
     */
    List<SubPostVO> toSubVO(List<SysPost> postList);
}
