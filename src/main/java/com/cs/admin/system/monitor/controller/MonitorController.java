package com.cs.admin.system.monitor.controller;

import com.cs.admin.system.monitor.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统监控接口
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:23
 */
@Api(tags = "系统监控接口")
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;

    /**
     * 系统监控信息
     *
     * @return info
     */
    @ApiOperation(value = "查询监控信息", notes = "查询监控信息")
    @GetMapping("/monitor")
    @PreAuthorize("@ck.permit('sys:monitor')")
    public ResponseEntity<?> monitor() {
        return ResponseEntity.ok(monitorService.monitor());
    }

}
