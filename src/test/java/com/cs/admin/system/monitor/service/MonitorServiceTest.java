package com.cs.admin.system.monitor.service;

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
 * @since 2021/3/12 9:52
 */
@SpringBootTest
class MonitorServiceTest {

    @Autowired
    private MonitorService monitorService;


    @Test
    public void monitor(){
        monitorService.monitor();
    }


}