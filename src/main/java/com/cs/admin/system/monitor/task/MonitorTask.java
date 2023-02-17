package com.cs.admin.system.monitor.task;

import com.cs.admin.system.monitor.service.MonitorService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/4/23 17:06
 */
@Component
@AllArgsConstructor
public class MonitorTask {

    private final MonitorService monitorService;

    @Scheduled(fixedRate = 4000)
    public void refresh(){
        monitorService.refresh();
    }

}
