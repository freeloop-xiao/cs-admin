package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.dto.RolePageDTO;
import com.cs.admin.system.manager.domain.entity.SysRole;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import com.cs.admin.system.manager.service.SysRoleService;
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
 * @since 2021/2/2 9:46
 */
@SpringBootTest
class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void add() {
        RoleAddDTO addDTO = new RoleAddDTO();
        addDTO.setRoleCode("admin");
        addDTO.setRoleName("管理员用户");
        addDTO.setDescription("超级管理员");
        addDTO.setOrderNum(1);
        sysRoleService.add(addDTO);
    }

    @Test
    void del() {
        sysRoleService.del(1L);
    }

    @Test
    void edit() {
        RoleEditDTO editDTO = new RoleEditDTO();
        editDTO.setRoleId(2L);
        editDTO.setRoleName("测试");
        editDTO.setRoleCode("test");
        editDTO.setStatus(true);
        sysRoleService.edit(editDTO);
        System.out.println(JSON.toJSONString(sysRoleService.getVO(2L)));
    }

    @Test
    void getVO() {
        System.out.println(sysRoleService.getVO(1L));
    }

    @Test
    void page() {
        RolePageDTO pageDTO = new RolePageDTO();
        pageDTO.setKey("test");
        PageVO<RoleVO> pageVO = sysRoleService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void list() {
        List<RoleVO> list = sysRoleService.list("test");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void getByRoleIds() {
        List<SysRole> list = sysRoleService.getByRoleIds(new HashSet<>(Arrays.asList(1L,2L)));
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void getByUserId() {
        List<RoleVO> list = sysRoleService.getByUserId(1L);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    void getSubRoleByUserId() {
        List<SubRoleVO> subRoleVOS = sysRoleService.getSubRoleByUserId(1L);
        System.out.println(JSON.toJSONString(subRoleVOS));
    }
}