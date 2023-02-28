package com.cs.admin.system.manager.domain.covert;

import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.vo.MenuVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SysMenuConvertImpl implements SysMenuConvert {

    @Override
    public SysMenu toEntity(MenuAddDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setParentMenuId( dto.getParentMenuId() );
        sysMenu.setMenuSort( dto.getMenuSort() );
        sysMenu.setType( dto.getType() );
        sysMenu.setTitle( dto.getTitle() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setIcon( dto.getIcon() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setIsFrame( dto.getIsFrame() );
        sysMenu.setHidden( dto.getHidden() );
        sysMenu.setPermission( dto.getPermission() );
        sysMenu.setStatus( dto.getStatus() );
        sysMenu.setRemark( dto.getRemark() );

        return sysMenu;
    }

    @Override
    public MenuVO toVO(SysMenu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuVO menuVO = new MenuVO();

        menuVO.setMenuId( entity.getMenuId() );
        menuVO.setParentMenuId( entity.getParentMenuId() );
        menuVO.setMenuSort( entity.getMenuSort() );
        menuVO.setSubCount( entity.getSubCount() );
        menuVO.setType( entity.getType() );
        menuVO.setTitle( entity.getTitle() );
        menuVO.setComponent( entity.getComponent() );
        menuVO.setIcon( entity.getIcon() );
        menuVO.setPath( entity.getPath() );
        menuVO.setIsFrame( entity.getIsFrame() );
        menuVO.setHidden( entity.getHidden() );
        menuVO.setPermission( entity.getPermission() );
        menuVO.setStatus( entity.getStatus() );
        menuVO.setRemark( entity.getRemark() );

        return menuVO;
    }

    @Override
    public List<SysMenu> toEntity(List<MenuAddDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysMenu> list = new ArrayList<SysMenu>( dtoList.size() );
        for ( MenuAddDTO menuAddDTO : dtoList ) {
            list.add( toEntity( menuAddDTO ) );
        }

        return list;
    }

    @Override
    public List<MenuVO> toVO(List<SysMenu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuVO> list = new ArrayList<MenuVO>( entityList.size() );
        for ( SysMenu sysMenu : entityList ) {
            list.add( toVO( sysMenu ) );
        }

        return list;
    }

    @Override
    public SysMenu toEntity(MenuEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setMenuId( dto.getMenuId() );
        sysMenu.setParentMenuId( dto.getParentMenuId() );
        sysMenu.setMenuSort( dto.getMenuSort() );
        sysMenu.setType( dto.getType() );
        sysMenu.setTitle( dto.getTitle() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setIcon( dto.getIcon() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setIsFrame( dto.getIsFrame() );
        sysMenu.setHidden( dto.getHidden() );
        sysMenu.setPermission( dto.getPermission() );
        sysMenu.setStatus( dto.getStatus() );
        sysMenu.setRemark( dto.getRemark() );

        return sysMenu;
    }
}
