package com.cs.admin.system.manager.controller;

import cn.hutool.core.lang.tree.Tree;
import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.MenuAddDTO;
import com.cs.admin.system.manager.domain.dto.MenuEditDTO;
import com.cs.admin.system.manager.domain.dto.MenuPageDTO;
import com.cs.admin.system.manager.domain.vo.MenuVO;
import com.cs.admin.system.manager.service.SysMenuService;
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
 * 系统菜单 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-22
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
public class SysMenuController extends BaseController {

    private final SysMenuService sysMenuService;


    /**
     * 获取登陆菜单树
     *
     * @return tree
     */
    @ApiOperation(value = "登陆后查询菜单树", notes = "登陆后查询菜单树")
    @GetMapping("/build")
    public ResponseEntity<List<Tree<Long>>> build() {
        return ResponseEntity.ok(sysMenuService.build(getUserId()));
    }


    /**
     * 添加菜单
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @PostMapping
    @PreAuthorize("@ck.permit('menu:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody MenuAddDTO addDTO) {
        sysMenuService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return success/false
     */
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @DeleteMapping("/{menuId}")
    @PreAuthorize("@ck.permit('menu:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long menuId) {
        sysMenuService.del(menuId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PutMapping
    @PreAuthorize("@ck.permit('menu:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody MenuEditDTO editDTO) {
        sysMenuService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询
     *
     * @param menuId ID
     * @return SysMenu
     */
    @ApiOperation(value = "获取菜单详情", notes = "获取菜单详情")
    @GetMapping("/{menuId}")
    @PreAuthorize("@ck.permit('menu:get')")
    public ResponseEntity<Object> get(@PathVariable Long menuId) {
        return ResponseEntity.ok(sysMenuService.getMenuVO(menuId));
    }


    /**
     * 分页查询信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('menu:page')")
    public ResponseEntity<PageVO<MenuVO>> page(@Valid @RequestBody MenuPageDTO pageDTO) {
        return ResponseEntity.ok(sysMenuService.page(pageDTO));
    }


    /**
     * 查询子菜单列表
     *
     * @param parentMenuId 父菜单ID
     * @return list
     */
    @ApiOperation(value = "查询子菜单列表", notes = "查询子菜单列表")
    @GetMapping("/list/{parentMenuId}")
    @PreAuthorize("@ck.permit('menu:list')")
    public ResponseEntity<List<MenuVO>> list(@PathVariable Long parentMenuId) {
        return ResponseEntity.ok(sysMenuService.list(parentMenuId));
    }

    /**
     * 查询系统菜单树
     *
     * @return tree
     */
    @ApiOperation(value = "查询系统菜单树", notes = "查询系统菜单树")
    @GetMapping("/tree")
    @PreAuthorize("@ck.permit('menu:tree')")
    public ResponseEntity<List<Tree<Long>>> tree() {
        return ResponseEntity.ok(sysMenuService.tree());
    }

}
