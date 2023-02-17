package com.cs.admin.system.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.log.domain.dto.LogPageDTO;
import com.cs.admin.system.log.domain.entity.SysLog;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-16
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 记录日志
     *
     * @param sysLog sysLog
     */
    void add(SysLog sysLog);


    /**
     * 分页查询日志
     *
     * @param logPageDTO 日志pageDTO
     * @return page
     */
    PageVO<SysLog> page(LogPageDTO logPageDTO);

}
