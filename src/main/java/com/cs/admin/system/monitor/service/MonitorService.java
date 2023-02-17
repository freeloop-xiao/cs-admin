package com.cs.admin.system.monitor.service;

import com.cs.admin.system.monitor.domain.vo.SysMonitorVO;

/**
 * <p>
 * 监控业务实现
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:26
 */
public interface MonitorService {

    /**
     * 查询服务部署服务器信息
     *
     * @return sysMonitor
     */
    SysMonitorVO monitor();

    /**
     * 刷新监控信息
     */
    void refresh();

}
