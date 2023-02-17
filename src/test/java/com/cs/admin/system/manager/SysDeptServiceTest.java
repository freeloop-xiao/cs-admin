package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.dto.DeptPageDTO;
import com.cs.admin.system.manager.domain.vo.DeptVO;
import com.cs.admin.system.manager.service.SysDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/1 16:56
 */
@SpringBootTest
class SysDeptServiceTest {

    @Autowired
    private SysDeptService sysDeptService;

    @Test
    void add() {

        DeptAddDTO addDTO = new DeptAddDTO();
        addDTO.setDeptName("深圳分公司");
        addDTO.setParentId(4L);
        sysDeptService.add(addDTO);

        addDTO.setParentId(5L);
        addDTO.setDeptName("长沙分公司");
        sysDeptService.add(addDTO);
    }

    @Test
    void del() {
        sysDeptService.del(1L);
    }

    @Test
    void edit() {
        DeptEditDTO editDTO = new DeptEditDTO();
        editDTO.setDeptId(1L);
        editDTO.setOrderNum(10);
        sysDeptService.edit(editDTO);

    }

    @Test
    void getVO() {
        System.out.println(sysDeptService.getVO(1L));
        System.out.println(sysDeptService.getVO(10L));
    }

    @Test
    void page() {
        DeptPageDTO pageDTO = new DeptPageDTO();
        pageDTO.setKey("公司");
        PageVO<DeptVO> pageVO = sysDeptService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }
}