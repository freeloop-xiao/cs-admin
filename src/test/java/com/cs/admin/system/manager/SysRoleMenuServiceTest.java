package com.cs.admin.system.manager;

import com.cs.admin.system.manager.service.SysRoleMenuService;
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
 * @since 2021/2/2 11:08
 */
@SpringBootTest
class SysRoleMenuServiceTest {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Test
    void countByMenuId() {
        System.out.println(sysRoleMenuService.countByMenuId(1L));
    }

    @Test
    void removeByRoleId() {
    }

    @Test
    void getMenuIdsByRoleId() {
    }

    @Test
    void addByRoleIdAndMenuIds() {
    }

    @Test
    void removeByRoleIdAndMenuIds() {
    }
}