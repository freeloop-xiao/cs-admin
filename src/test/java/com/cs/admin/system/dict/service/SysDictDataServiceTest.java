package com.cs.admin.system.dict.service;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.dto.DictDataAddDTO;
import com.cs.admin.system.dict.domain.dto.DictDataEditDTO;
import com.cs.admin.system.dict.domain.dto.DictDataPageDTO;
import com.cs.admin.system.dict.domain.vo.DictDataVO;
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
 * @since 2021/2/2 15:53
 */
@SpringBootTest
class SysDictDataServiceTest {

    @Autowired
    private SysDictDataService sysDictDataService;

    @Test
    void add() {
        DictDataAddDTO addDTO = new DictDataAddDTO();
        addDTO.setDictCode("free-loop");
        addDTO.setDictLabel("test-label2");
        addDTO.setDictValue("hell world2");
        sysDictDataService.add(addDTO);
        addDTO.setDictCode("free-loop");
        addDTO.setDictLabel("test-label3");
        addDTO.setDictValue("hell world3");
        sysDictDataService.add(addDTO);
    }

    @Test
    void del() {
        sysDictDataService.del(2L);
    }

    @Test
    void edit() {
        DictDataEditDTO editDTO = new DictDataEditDTO();
        editDTO.setDataId(1L);
        editDTO.setDictCode("free-loop");
        editDTO.setDictValue("fuck you");
        sysDictDataService.edit(editDTO);

    }

    @Test
    void getVO() {
        System.out.println(JSON.toJSONString(sysDictDataService.getVO(1L)));
    }

    @Test
    void getByDictCode() {
        System.out.println(JSON.toJSONString(sysDictDataService.getByDictCode("free-loop")));
    }

    @Test
    void getListByDictCodeParentDataId() {
        System.out.println(JSON.toJSONString(sysDictDataService.getListByDictCodeParentDataId("free-loop", 0L)));
    }

    @Test
    void page() {
        DictDataPageDTO pageDTO = new DictDataPageDTO();
        pageDTO.setKey("test");
        PageVO<DictDataVO> pageVO = sysDictDataService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void removeByDictCode() {
    }
}