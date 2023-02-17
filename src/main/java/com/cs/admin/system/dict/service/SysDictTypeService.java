package com.cs.admin.system.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.dto.DictTypeAddDTO;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.dto.DictTypePageDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 新增字典类型
     *
     * @param addDTO 字典类型新增DTO
     * @return true/false
     */
    Boolean add(DictTypeAddDTO addDTO);

    /**
     * 删除字典类型
     *
     * @param dictId 字典ID
     * @return true/false
     */
    Boolean del(Long dictId);

    /**
     * 编辑字典类型
     *
     * @param editDTO 编辑传输DTO
     * @return true/false
     */
    Boolean edit(DictTypeEditDTO editDTO);

    /**
     * 获取单个字典类型
     *
     * @param dictId 字典ID
     * @return vo
     */
    DictTypeVO getVO(Long dictId);

    /**
     * 分页查询字典类型
     *
     * @param pageDTO 分页查询DTO
     * @return pageVO
     */
    PageVO<DictTypeVO> page(DictTypePageDTO pageDTO);

    /**
     * 检查字典编码
     *
     * @param dictCode 字典编码
     * @return sysDictType
     */
    SysDictType getByDictCode(String dictCode);
}
