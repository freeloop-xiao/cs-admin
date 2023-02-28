package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.vo.PostVO;
import com.cs.admin.system.manager.domain.vo.SubPostVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysPostConvertImpl implements SysPostConvert {

    @Override
    public SysPost toEntity(PostAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysPost sysPost = new SysPost();

        sysPost.setPostCode( dto.getPostCode() );
        sysPost.setPostName( dto.getPostName() );
        sysPost.setPostSort( dto.getPostSort() );
        sysPost.setStatus( dto.getStatus() );
        sysPost.setRemark( dto.getRemark() );

        return sysPost;
    }

    @Override
    public PostVO toVO(SysPost entity) {
        if ( entity == null ) {
            return null;
        }

        PostVO postVO = new PostVO();

        postVO.setPostId( entity.getPostId() );
        postVO.setPostCode( entity.getPostCode() );
        postVO.setPostName( entity.getPostName() );
        postVO.setPostSort( entity.getPostSort() );
        postVO.setStatus( entity.getStatus() );
        postVO.setCreateTime( entity.getCreateTime() );
        postVO.setRemark( entity.getRemark() );

        return postVO;
    }

    @Override
    public List<SysPost> toEntity(List<PostAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysPost> list = new ArrayList<SysPost>( dtoList.size() );
        for ( PostAddDTO postAddDTO : dtoList ) {
            list.add( toEntity( postAddDTO ) );
        }

        return list;
    }

    @Override
    public List<PostVO> toVO(List<SysPost> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PostVO> list = new ArrayList<PostVO>( entityList.size() );
        for ( SysPost sysPost : entityList ) {
            list.add( toVO( sysPost ) );
        }

        return list;
    }

    @Override
    public SysPost toEntity(PostEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysPost sysPost = new SysPost();

        sysPost.setPostId( dto.getPostId() );
        sysPost.setPostCode( dto.getPostCode() );
        sysPost.setPostName( dto.getPostName() );
        sysPost.setPostSort( dto.getPostSort() );
        sysPost.setStatus( dto.getStatus() );
        sysPost.setRemark( dto.getRemark() );

        return sysPost;
    }

    @Override
    public SubPostVO toSubVO(SysPost post) {
        if ( post == null ) {
            return null;
        }

        SubPostVO subPostVO = new SubPostVO();

        subPostVO.setPostId( post.getPostId() );
        subPostVO.setPostCode( post.getPostCode() );
        subPostVO.setPostName( post.getPostName() );

        return subPostVO;
    }

    @Override
    public List<SubPostVO> toSubVO(List<SysPost> postList) {
        if ( postList == null ) {
            return null;
        }

        List<SubPostVO> list = new ArrayList<SubPostVO>( postList.size() );
        for ( SysPost sysPost : postList ) {
            list.add( toSubVO( sysPost ) );
        }

        return list;
    }
}
