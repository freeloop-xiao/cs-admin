package com.cs.admin.system.manager.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PermissionAddDTO;
import com.cs.admin.system.manager.domain.dto.PermissionEditDTO;
import com.cs.admin.system.manager.domain.dto.PermissionPageDTO;
import com.cs.admin.system.manager.domain.vo.PermissionVO;
import com.cs.admin.system.manager.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 权限编码信息表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Api(tags = "权限管理接口")
@RestController
@RequestMapping("/sys/permission")
@AllArgsConstructor
public class SysPermissionController extends BaseController {

    private final SysPermissionService sysPermissionService;

    /**
     * 添加权限
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "增加权限", notes = "增加权限")
    @PostMapping
    @PreAuthorize("@ck.permit('permit:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody PermissionAddDTO addDTO) {
        sysPermissionService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除权限
     *
     * @param permissionId ID
     * @return success/false
     */
    @ApiOperation(value = "删除权限", notes = "删除权限")
    @DeleteMapping("/{permissionId}")
    @PreAuthorize("@ck.permit('permit:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long permissionId) {
        sysPermissionService.del(permissionId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑权限
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改权限", notes = "修改权限")
    @PutMapping
    @PreAuthorize("@ck.permit('permit:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody PermissionEditDTO editDTO) {
        sysPermissionService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询权限
     *
     * @param permissionId ID
     * @return SysPermission
     */
    @ApiOperation(value = "获取权限详情", notes = "获取权限详情")
    @GetMapping("/{permissionId}")
    @PreAuthorize("@ck.permit('permit:get')")
    public ResponseEntity<PermissionVO> get(@PathVariable Long permissionId) {
        return ResponseEntity.ok(sysPermissionService.getVO(permissionId));
    }


    /**
     * 分页查询权限信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询列表", notes = "分页查询列表")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('permit:page')")
    public ResponseEntity<PageVO<PermissionVO>> page(@Valid @RequestBody PermissionPageDTO pageDTO) {
        return ResponseEntity.ok(sysPermissionService.page(pageDTO));
    }


    /**
     * 列表查询信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "查询列表关键字模糊查询(权限名称|权限编码|接口地址|描叙)", notes = "查询列表关键字模糊查询(权限名称|权限编码|接口地址|描叙)")
    @GetMapping("/list/{key}")
    @PreAuthorize("@ck.permit('permit:list')")
    public ResponseEntity<List<PermissionVO>> list(@PathVariable String key) {
        return ResponseEntity.ok(sysPermissionService.list(key));
    }
}
