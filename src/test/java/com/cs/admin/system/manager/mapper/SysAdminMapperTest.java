package com.cs.admin.system.manager.mapper;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.admin.system.manager.domain.entity.SysAdmin;
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
 * @since 2021/4/23 14:34
 */
@SpringBootTest
class SysAdminMapperTest {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Test
    void selectAdminPage() {
        Page<SysAdmin> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);
        IPage<SysAdmin> result = sysAdminMapper.selectAdminPage(page,1L,null,null,null,null,"360");
        System.out.println(JSON.toJSONString(result));
    }
}