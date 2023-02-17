package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.dto.MenuPageDTO;
import com.cs.admin.system.manager.domain.entity.SysMenu;
import com.cs.admin.system.manager.domain.vo.MenuVO;
import com.cs.admin.system.manager.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/2 9:15
 */
@SpringBootTest
class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void build() {
        sysMenuService.build(1L);
    }

    @Test
    void add() {
        MenuAddDTO addDTO = new MenuAddDTO();
        addDTO.setParentMenuId(4L);
        addDTO.setType(1);
//        addDTO.setTitle("代码生成");
//        sysMenuService.add(addDTO);
//
//        addDTO.setParentMenuId(0L);
//        addDTO.setTitle("存储管理");
//        sysMenuService.add(addDTO);
//
//        addDTO.setTitle("邮件管理");
//        sysMenuService.add(addDTO);
//
//        addDTO.setTitle("支付宝工具");
//        sysMenuService.add(addDTO);

        addDTO.setTitle("测试管理");
        sysMenuService.add(addDTO);
    }

    @Test
    void del() {
        sysMenuService.del(25L);
    }

    @Test
    void edit() {
        MenuEditDTO editDTO = new MenuEditDTO();
        editDTO.setMenuId(24L);
        editDTO.setParentMenuId(1L);
        editDTO.setType(1);
        editDTO.setTitle("支付宝测试");
        sysMenuService.edit(editDTO);
    }

    @Test
    void getMenuVO() {
        MenuVO menuVO = sysMenuService.getMenuVO(1L);
        System.out.println(JSON.toJSONString(menuVO));
    }

    @Test
    void page() {
        MenuPageDTO pageDTO = new MenuPageDTO();
        pageDTO.setParentMenuId(0L);
        PageVO<MenuVO> pageVO = sysMenuService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));

    }

    @Test
    void list() {
        List<MenuVO> list = sysMenuService.list(0L);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void getMenusByIds() {
        List<SysMenu> list = sysMenuService.getMenusByIds(new HashSet<>(Arrays.asList(1L,2L)));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void getMenusByRoleId() {
    }
}