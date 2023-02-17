package com.cs.admin.system.manager.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.dto.PostPageDTO;
import com.cs.admin.system.manager.domain.vo.PostVO;
import com.cs.admin.system.manager.service.SysPostService;
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
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-24
 */
@Api(tags = "岗位管理接口")
@RestController
@RequestMapping("/sys/post")
@AllArgsConstructor
public class SysPostController extends BaseController {

    private final SysPostService sysPostService;


    /**
     * 添加岗位
     *
     * @param addDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "增加岗位", notes = "增加岗位")
    @PostMapping
    @PreAuthorize("@ck.permit('post:add')")
    public ResponseEntity<Boolean> add(@Valid @RequestBody PostAddDTO addDTO) {
        sysPostService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除岗位
     *
     * @param postId ID
     * @return success/false
     */
    @ApiOperation(value = "删除岗位", notes = "删除岗位")
    @DeleteMapping("/{postId}")
    @PreAuthorize("@ck.permit('post:del')")
    public ResponseEntity<Boolean> delete(@PathVariable Long postId) {
        sysPostService.del(postId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑岗位
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改岗位", notes = "修改岗位")
    @PutMapping
    @PreAuthorize("@ck.permit('post:edit')")
    public ResponseEntity<Boolean> edit(@Valid @RequestBody PostEditDTO editDTO) {
        sysPostService.edit(editDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 通过ID查询岗位信息
     *
     * @param postId ID
     * @return PostVO
     */
    @ApiOperation(value = "ID获取岗位详情", notes = "ID获取岗位详情")
    @GetMapping("/{postId}")
    @PreAuthorize("@ck.permit('post:get')")
    public ResponseEntity<PostVO> get(@PathVariable Long postId) {
        return ResponseEntity.ok(sysPostService.getVO(postId));
    }


    /**
     * 分页查询岗位信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页获取岗位", notes = "分页获取岗位")
    @PostMapping("/page")
    @PreAuthorize("@ck.permit('post:page')")
    public ResponseEntity<PageVO<PostVO>> page(@Valid @RequestBody PostPageDTO pageDTO) {
        return ResponseEntity.ok(sysPostService.page(pageDTO));
    }

}
