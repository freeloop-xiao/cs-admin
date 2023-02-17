package com.cs.admin.system.dict.controller;

import cn.hutool.core.lang.tree.Tree;
import com.cs.admin.common.controller.BaseController;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.dto.*;
import com.cs.admin.system.dict.domain.vo.DictDataVO;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;
import com.cs.admin.system.dict.service.SysDictDataService;
import com.cs.admin.system.dict.service.SysDictTypeService;
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
 * 字典数据表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Api(tags = "字典接口")
@RestController
@RequestMapping("/sys/dict")
@AllArgsConstructor
public class SysDictController extends BaseController {

    private final SysDictTypeService sysDictTypeService;

    private final SysDictDataService sysDictDataService;

    /**
     * 添加字典类型
     *
     * @param addDTO 字典类型新增
     * @return success/false
     */
    @ApiOperation(value = "新增字典类型", notes = "新增字典类型")
    @PostMapping("/type")
    @PreAuthorize("@ck.permit('dict:type:add')")
    public ResponseEntity<Boolean> addType(@Valid @RequestBody DictTypeAddDTO addDTO) {
        sysDictTypeService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除字典类型
     *
     * @param dictId ID
     * @return success/false
     */
    @ApiOperation(value = "删除字典类型", notes = "删除字典类型")
    @DeleteMapping("/type/{dictId}")
    @PreAuthorize("@ck.permit('dict:type:del')")
    public ResponseEntity<Boolean> deleteType(@PathVariable Long dictId) {
        sysDictTypeService.del(dictId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑字典类型
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改字典类型", notes = "修改字典类型")
    @PutMapping("/type")
    @PreAuthorize("@ck.permit('dict:type:edit')")
    public ResponseEntity<Boolean> editType(@Valid @RequestBody DictTypeEditDTO editDTO) {
        sysDictTypeService.edit(editDTO);
        return ResponseEntity.noContent().build();

    }

    /**
     * 通过ID查询字典类型
     *
     * @param dictId ID
     * @return SysDictData
     */
    @ApiOperation(value = "查询字典类型", notes = "查询字典类型")
    @GetMapping("/type/{dictId}")
    public ResponseEntity<DictTypeVO> getType(@PathVariable Long dictId) {
        return ResponseEntity.ok(sysDictTypeService.getVO(dictId));
    }


    /**
     * 分页查询字典类型信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询字典类型", notes = "分页查询字典类型")
    @PostMapping("/type/page")
    public ResponseEntity<?> pageType(@Valid @RequestBody DictTypePageDTO pageDTO) {
        return ResponseEntity.ok(sysDictTypeService.page(pageDTO));
    }


    // =================字典数据查询接口========================================================================


    /**
     * 添加 字典数据
     *
     * @param addDTO 字典类型新增
     * @return success/false
     */
    @ApiOperation(value = "新增字典数据", notes = "新增字典数据")
    @PostMapping("/data")
    @PreAuthorize("@ck.permit('dict:data:add')")
    public ResponseEntity<Boolean> addData(@Valid @RequestBody DictDataAddDTO addDTO) {
        sysDictDataService.add(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除 字典数据
     *
     * @param dataId ID
     * @return success/false
     */
    @ApiOperation(value = "删除字典数据", notes = "删除字典数据")
    @DeleteMapping("/data/{dataId}")
    @PreAuthorize("@ck.permit('dict:data:del')")
    public ResponseEntity<Boolean> deleteData(@PathVariable Long dataId) {
        sysDictDataService.del(dataId);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑字典数据
     *
     * @param editDTO 实体
     * @return success/false
     */
    @ApiOperation(value = "修改字典数据", notes = "修改字典数据")
    @PutMapping("/data")
    @PreAuthorize("@ck.permit('dict:data:edit')")
    public ResponseEntity<Boolean> editData(@Valid @RequestBody DictDataEditDTO editDTO) {
        sysDictDataService.edit(editDTO);
        return ResponseEntity.noContent().build();

    }

    /**
     * 通过ID查询字典数据
     *
     * @param dataId ID
     * @return DictDataVO
     */
    @ApiOperation(value = "查询字典数据", notes = "查询字典数据")
    @GetMapping("/data/{dataId}")
    public ResponseEntity<DictDataVO> getData(@PathVariable Long dataId) {
        return ResponseEntity.ok(sysDictDataService.getVO(dataId));
    }

    @ApiOperation(value = "查询字典数据列表", notes = "查询字典数据列表")
    @GetMapping("/data/list/{dictCode}/{parentDataId}")
    public ResponseEntity<List<Tree<Long>>> getDataList(@PathVariable(name = "dictCode") String dictCode,
                                                        @PathVariable(name = "parentDataId", required = false) Long parentDataId) {
        return ResponseEntity.ok(sysDictDataService.getListByDictCodeParentDataId(dictCode, parentDataId));
    }

    /**
     * 分页查询字典数据信息
     *
     * @param pageDTO 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询字典数据", notes = "分页查询字典数据")
    @PostMapping("/data/page")
    public ResponseEntity<PageVO<DictDataVO>> pageData(@Valid @RequestBody DictDataPageDTO pageDTO) {
        return ResponseEntity.ok(sysDictDataService.page(pageDTO));
    }


}
