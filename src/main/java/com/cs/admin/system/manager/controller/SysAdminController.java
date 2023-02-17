package com.cs.admin.system.manager.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.system.manager.domain.dto.AdminAddDTO;
import com.cs.admin.system.manager.domain.dto.AdminEditDTO;
import com.cs.admin.system.manager.domain.dto.AdminEditPwdDTO;
import com.cs.admin.system.manager.domain.dto.AdminPageDTO;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import com.cs.admin.system.manager.service.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 系统管理员表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/sys/admin")
@AllArgsConstructor
public class SysAdminController extends BaseController {

    private final SysAdminService sysAdminService;


    @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
    @GetMapping("/get-my")
    public ResponseEntity<AdminVO> getMy() {
        return ResponseEntity.ok(sysAdminService.getMyVO(getUserId()));
    }


    /**
     * 添加
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping
    @PreAuthorize("@ck.permit('user:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody AdminAddDTO addDTO) {
        sysAdminService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除用户
     *
     * @param userId ID
     * @return success/false
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/{userId}")
    @PreAuthorize("@ck.permit('user:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long userId) {
        sysAdminService.del(userId);
        return ResponseEntity.ok().build();

    }

    /**
     * 编辑
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PutMapping
    @PreAuthorize("@ck.permit('user:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody AdminEditDTO editDTO) {
        sysAdminService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询
     *
     * @param userId ID
     * @return AdminVO
     */
    @ApiOperation(value = "获取用户详细", notes = "获取用户详细")
    @GetMapping("/{userId}")
    @PreAuthorize("@ck.permit('user:get')")
    public ResponseEntity<AdminVO> get(@PathVariable Long userId) {
        return ResponseEntity.ok(sysAdminService.getVO(userId));
    }


    /**
     * 分页查询信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象S
     */
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('user:page')")
    public ResponseEntity<?> page(@Valid @RequestBody AdminPageDTO pageDTO) {
        return ResponseEntity.ok(sysAdminService.page(pageDTO));
    }


    /**
     * 编辑当前用户密码
     *
     * @param editPwdDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改当前用户密码", notes = "修改当前用户密码")
    @PutMapping("/pwd")
    public ResponseEntity<Boolean> editPwd(@Valid @RequestBody AdminEditPwdDTO editPwdDTO) {
        sysAdminService.reSetPwd(editPwdDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 修改其他用户密码
     *
     * @param editPwdDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @PutMapping("/reset-pwd")
    @PreAuthorize("@ck.permit('user:pwd:reset')")
    public ResponseEntity<Boolean> editOtherPwd(@Valid @RequestBody AdminEditPwdDTO editPwdDTO) {
        sysAdminService.reSetOtherPwd(editPwdDTO);
        return ResponseEntity.noContent().build();
    }

}
