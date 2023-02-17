package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.AdminAddDTO;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.dto.AdminEditDTO;
import com.cs.admin.system.manager.domain.dto.AdminPageDTO;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import com.cs.admin.system.manager.service.SysAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/1 16:51
 */
@SpringBootTest
class SysAdminServiceTest {

    @Autowired
    private SysAdminService sysAdminService;

    @Test
    void add() {
        AdminAddDTO addDTO = new AdminAddDTO();
        addDTO.setAccount("admins");
        addDTO.setPhone("120");
        addDTO.setEmail("freeloops@qq.com");
        addDTO.setPassword("free-loops");
        addDTO.setUserName("free-loops");
        addDTO.setNickName("test-2");
        addDTO.setRoleIds(new HashSet<>(Arrays.asList(1L)));
        sysAdminService.add(addDTO);
    }

    @Test
    void del() {
        sysAdminService.del(2L);
    }

    @Test
    void edit() {
        AdminEditDTO editDTO = new AdminEditDTO();
        editDTO.setUserId(1L);
        editDTO.setDeptId(2L);
        editDTO.setAccount("admin");
        editDTO.setRoleIds(new HashSet<>(Arrays.asList(1L,2L)));
        sysAdminService.edit(editDTO);
    }

    @Test
    void getVO() {
        AdminVO adminVO = sysAdminService.getVO(1L);
        System.out.println(JSON.toJSONString(adminVO));
    }

    @Test
    void page() {
        AdminPageDTO pageDTO = new AdminPageDTO();
        pageDTO.setKey("admin");
        PageVO<AdminVO> pageVO = sysAdminService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void getAdminDtoByAccount() {
        AdminDTO adminDTO = sysAdminService.getAdminDtoByAccount("admin");
        System.out.println(JSON.toJSONString(adminDTO));
    }
}