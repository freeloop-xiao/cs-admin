package com.cs.admin.system.dict.service;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.dto.DictTypeAddDTO;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.dto.DictTypePageDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;
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
 * @since 2021/2/2 15:01
 */
@SpringBootTest
class SysDictTypeServiceTest {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Test
    void add() {
        DictTypeAddDTO addDTO = new DictTypeAddDTO();
        addDTO.setDictCode("test_code");
        addDTO.setDictName("测试编码");
        addDTO.setRemark("测试");
        sysDictTypeService.add(addDTO);
    }

    @Test
    void del() {
        sysDictTypeService.del(1L);
    }

    @Test
    void edit() {
        DictTypeEditDTO editDTO = new DictTypeEditDTO();
        editDTO.setDictId(2L);
        editDTO.setDictName("测试");
        editDTO.setDictCode("free-loop");
        editDTO.setRemark("remark测试");
        sysDictTypeService.edit(editDTO);
    }

    @Test
    void getVO() {
        DictTypeVO dictTypeVO = sysDictTypeService.getVO(2L);
        System.out.println(JSON.toJSONString(dictTypeVO));
    }

    @Test
    void page() {
        DictTypePageDTO pageDTO = new DictTypePageDTO();
        pageDTO.setKey("测试");
        PageVO<DictTypeVO> pageVO = sysDictTypeService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void getByDictCode() {
        SysDictType sysDictType = sysDictTypeService.getByDictCode("free-loop");
        System.out.println(JSON.toJSONString(sysDictType));
    }
}