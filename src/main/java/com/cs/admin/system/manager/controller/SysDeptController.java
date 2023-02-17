package com.cs.admin.system.manager.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.system.manager.domain.dto.DeptAddDTO;
import com.cs.admin.system.manager.domain.dto.DeptEditDTO;
import com.cs.admin.system.manager.domain.dto.DeptPageDTO;
import com.cs.admin.system.manager.domain.vo.DeptVO;
import com.cs.admin.system.manager.service.SysDeptService;
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
 * 部门表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Api(tags = "部门管理接口")
@RestController
@RequestMapping("/sys/dept")
@AllArgsConstructor
public class SysDeptController extends BaseController {

    private final SysDeptService sysDeptService;


    /**
     * 添加
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "添加部门", notes = "添加部门")
    @PostMapping
    @PreAuthorize("@ck.permit('dept:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody DeptAddDTO addDTO) {
        sysDeptService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     *
     * @param deptId ID
     * @return success/false
     */
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @DeleteMapping("/{deptId}")
    @PreAuthorize("@ck.permit('dept:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long deptId) {
        sysDeptService.del(deptId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改部门", notes = "修改部门")
    @PutMapping
    @PreAuthorize("@ck.permit('dept:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody DeptEditDTO editDTO) {
        sysDeptService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询
     *
     * @param deptId ID
     * @return deptVO
     */
    @ApiOperation(value = "部门ID获取详情", notes = "部门ID获取详情")
    @GetMapping("/{deptId}")
    @PreAuthorize("@ck.permit('dept:get')")
    public ResponseEntity<DeptVO> get(@PathVariable Long deptId) {
        return ResponseEntity.ok(sysDeptService.getVO(deptId));
    }


    /**
     * 分页查询信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页获取部门", notes = "分页获取部门")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('dept:page')")
    public ResponseEntity<?> page(@Valid @RequestBody DeptPageDTO pageDTO) {
        return ResponseEntity.ok(sysDeptService.page(pageDTO));
    }


    /**
     * 查询部门树
     *
     * @return tree
     */
    @ApiOperation(value = "查询部门树", notes = "查询部门树")
    @GetMapping("/tree")
    @PreAuthorize("@ck.permit('dept:tree')")
    public ResponseEntity<?> tree() {
        return ResponseEntity.ok(sysDeptService.tree());
    }

}
