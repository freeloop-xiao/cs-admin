package com.cs.admin.system.auth.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.system.auth.domain.dto.OnlinePageDTO;
import com.cs.admin.system.auth.service.OnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 21:39
 */

@Api(tags = "在线用户管理接口")
@RestController
@RequestMapping("/online")
@RequiredArgsConstructor
public class OnlineController extends BaseController {

    private final OnlineService onlineService;

    /**
     * 查询在线用户
     *
     * @param pageDTO 分页查询条件
     * @return page
     */
    @ApiOperation(value = "查询在线用户", notes = "查询在线用户")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('online:page')")
    public ResponseEntity<?> page(@RequestBody OnlinePageDTO pageDTO) {
        return ResponseEntity.ok(onlineService.page(pageDTO));
    }

    /**
     * 下载在线用户excel
     *
     * @param response 响应
     * @param keyWord  关键字
     */
    @ApiOperation(value = "下载在线用户", notes = "下载在线用户")
    @GetMapping("/download")
    @PreAuthorize("@ck.permit('online:down')")
    public void download(HttpServletResponse response, @RequestParam(required = false) String keyWord) {
        onlineService.download(response, keyWord);
    }

    /**
     * 踢出在线用户
     *
     * @param userIds 用户ID列表
     * @return true/false
     */
    @ApiOperation(value = "踢出在线用户", notes = "踢出在线用户")
    @DeleteMapping
    @PreAuthorize("@ck.permit('online:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> userIds) {
        onlineService.kickOut(userIds);
        return ResponseEntity.ok().build();
    }


}
