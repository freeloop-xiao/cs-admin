package com.cs.admin.system.manager.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.RoleAddDTO;
import com.cs.admin.system.manager.domain.dto.RoleEditDTO;
import com.cs.admin.system.manager.domain.dto.RolePageDTO;
import com.cs.admin.system.manager.domain.vo.RoleVO;
import com.cs.admin.system.manager.service.SysRoleService;
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
 * 角色信息表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/sys/role")
@AllArgsConstructor
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;

    /**
     * 添加
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "增加角色", notes = "增加角色")
    @PostMapping
    @PreAuthorize("@ck.permit('role:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody RoleAddDTO addDTO) {
        sysRoleService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     *
     * @param roleId ID
     * @return success/false
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("/{roleId}")
    @PreAuthorize("@ck.permit('role:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long roleId) {
        sysRoleService.del(roleId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping
    @PreAuthorize("@ck.permit('role:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody RoleEditDTO editDTO) {
        sysRoleService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询
     *
     * @param roleId ID
     * @return SysRole
     */
    @ApiOperation(value = "获取角色详情", notes = "获取角色详情")
    @GetMapping("/{roleId}")
    @PreAuthorize("@ck.permit('role:get')")
    public ResponseEntity<RoleVO> get(@PathVariable Long roleId) {
        return ResponseEntity.ok(sysRoleService.getVO(roleId));
    }


    /**
     * 分页查询信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('role:page')")
    public ResponseEntity<PageVO<?>> page(@Valid @RequestBody RolePageDTO pageDTO) {
        return ResponseEntity.ok(sysRoleService.page(pageDTO));
    }


    /**
     * 分页查询信息
     *
     * @param key 关键字
     * @return 关键字
     */
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @GetMapping("/list/{key}")
    @PreAuthorize("@ck.permit('role:list')")
    public ResponseEntity<List<RoleVO>> list(@PathVariable String key) {
        return ResponseEntity.ok(sysRoleService.list(key));
    }

}
