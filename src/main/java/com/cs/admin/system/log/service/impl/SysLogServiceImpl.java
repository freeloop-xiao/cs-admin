package com.cs.admin.system.log.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.RequestUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.log.domain.dto.LogPageDTO;
import com.cs.admin.system.log.domain.entity.SysLog;
import com.cs.admin.system.log.mapper.SysLogMapper;
import com.cs.admin.system.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-16
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Value("${ip.local-parsing}")
    private Boolean local;


    @Async
    @Override
    public void add(SysLog sysLog) {

        if (ObjectUtil.isNotNull(sysLog)) {
            if (local) {
                sysLog.setAddress(RequestUtil.getLocalCityInfo(sysLog.getRequestIp()));
            } else {
                sysLog.setAddress(RequestUtil.getHttpCityInfo(sysLog.getRequestIp()));
            }
            save(sysLog);
        }
    }

    @Override
    public PageVO<SysLog> page(LogPageDTO logPageDTO) {
        Page<SysLog> logPage = PageUtil.page(logPageDTO);

        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasText(logPageDTO.getLogType())) {
            queryWrapper.eq("log_type", logPageDTO.getLogType());
        }

        if (StringUtils.hasText(logPageDTO.getDescription())) {
            queryWrapper.like("description", logPageDTO.getDescription());
        }

        if (ObjectUtil.isNotNull(logPageDTO.getLogStatus())) {
            queryWrapper.eq("log_status", logPageDTO.getLogStatus());
        }

        Page<SysLog> page = page(logPage, queryWrapper);
        return PageVO.toVO(logPageDTO.getPage(), logPageDTO.getLimit(), page.getTotal(), page.getRecords());
    }
}
