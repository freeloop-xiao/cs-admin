package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionPageDTO;
import com.cs.admin.system.manager.domain.vo.PermissionVO;
import com.cs.admin.system.manager.service.SysPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/30 20:38
 */
@SpringBootTest
class SysPermissionServiceTest {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    void add() {
        // 用户新增接口
//        PermissionAddDTO addDTO = new PermissionAddDTO("新增用户","user:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除用户","user:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("新增用户","user:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("获取用户详细","user:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页查询用户","user:page");
//        sysPermissionService.add(addDTO);


        // 部门管理接口
//        PermissionAddDTO addDTO = new PermissionAddDTO("添加部门","dept:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除部门","dept:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("修改部门","dept:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("部门ID获取详情","dept:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页获取部门","dept:page");
//        sysPermissionService.add(addDTO);

        // 菜单管理接口
//        PermissionAddDTO addDTO = new PermissionAddDTO("新增菜单", "menu:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除菜单", "menu:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("修改菜单", "menu:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("获取菜单详情", "menu:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页查询菜单", "menu:page");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("查询子菜单列表", "menu:list");
//        sysPermissionService.add(addDTO);

        // 权限管理接口
//        PermissionAddDTO addDTO = new PermissionAddDTO("增加权限", "permit:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除权限", "'permit:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("修改权限", "permit:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("获取权限详情", "permit:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页查询列表", "permit:page");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("查询列表关键字模糊查询(权限名称|权限编码|接口地址|描叙)", "permit:list");
//        sysPermissionService.add(addDTO);


//        PermissionAddDTO addDTO = new PermissionAddDTO("增加岗位", "post:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除岗位", "'post:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("修改岗位", "post:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("ID获取岗位详情", "post:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页获取岗位", "post:page");
//        sysPermissionService.add(addDTO);

//        PermissionAddDTO addDTO = new PermissionAddDTO("增加角色", "role:add");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("删除角色", "'role:del");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("修改角色", "role:edit");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("获取角色详情", "role:get");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("分页查询角色", "role:page");
//        sysPermissionService.add(addDTO);
//        addDTO = new PermissionAddDTO("查询角色列表", "role:list");
//        sysPermissionService.add(addDTO);

    }

    @Test
    void del() {
//        PermissionAddDTO addDTO = new PermissionAddDTO("测试", "test:test");
//        sysPermissionService.add(addDTO);
        sysPermissionService.del(36L);
    }

    @Test
    void edit() {
        PermissionAddDTO addDTO = new PermissionAddDTO("测试", "test:test");
        sysPermissionService.add(addDTO);
        PermissionVO permissionVO = sysPermissionService.getVO(37L);
        System.out.println(JSON.toJSONString(permissionVO));
    }

    @Test
    void getVO() {
        PermissionVO permissionVO = sysPermissionService.getVO(4L);
        System.out.println(JSON.toJSONString(permissionVO));
    }

    @Test
    void list() {
        PermissionPageDTO pageDTO = new PermissionPageDTO();
        pageDTO.setKey("删除");
        PageVO<PermissionVO> pageVO = sysPermissionService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }
}