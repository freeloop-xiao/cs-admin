package com.cs.admin.system.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.log.domain.dto.LogPageDTO;
import com.cs.admin.system.log.domain.entity.SysLog;
import com.cs.admin.system.log.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-16
 */
@Api(tags = "日志接口")
@RestController
@RequestMapping("/sys/log")
@AllArgsConstructor
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;


    /**
     * 分页查询信息
     *
     * @param logPageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页获取", notes = "分页获取")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('log:page')")
    public ResponseEntity<PageVO<SysLog>> page(@RequestBody LogPageDTO logPageDTO) {
        return ResponseEntity.ok(sysLogService.page(logPageDTO));
    }


}
