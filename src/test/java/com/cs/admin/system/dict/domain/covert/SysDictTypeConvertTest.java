package com.cs.admin.system.dict.domain.covert;

import com.alibaba.fastjson.JSON;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 21:34
 */
class SysDictTypeConvertTest {

    @Autowired
    private SysDictTypeConvert sysDictTypeConvert;

    @Test
    void toEntity() {

        DictTypeEditDTO editDTO = new DictTypeEditDTO();
        editDTO.setDictCode("free loop");
        editDTO.setDictId(1L);
        editDTO.setDictName("测试");
        SysDictType sysDictType = sysDictTypeConvert.toEntity(editDTO);
        System.out.println(JSON.toJSONString(sysDictType));
    }
}