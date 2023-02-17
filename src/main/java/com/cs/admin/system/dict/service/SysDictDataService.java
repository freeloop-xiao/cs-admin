package com.cs.admin.system.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.dto.DictDataAddDTO;
import com.cs.admin.system.dict.domain.dto.DictDataEditDTO;
import com.cs.admin.system.dict.domain.dto.DictDataPageDTO;
import com.cs.admin.system.dict.domain.entity.SysDictData;
import com.cs.admin.system.dict.domain.vo.DictDataVO;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
public interface SysDictDataService extends IService<SysDictData> {


    /**
     * 新增字典数据
     *
     * @param addDTO dto
     * @return true/false
     */
    Boolean add(DictDataAddDTO addDTO);

    /**
     * 删除字典数据
     *
     * @param dataId 数据ID
     * @return true/false
     */
    Boolean del(Long dataId);

    /**
     * 编辑字典数据
     *
     * @param editDTO dto
     * @return true/false
     */
    Boolean edit(DictDataEditDTO editDTO);

    /**
     * 查询数据详情
     *
     * @param dataId 数据ID
     * @return true/false
     */
    DictDataVO getVO(Long dataId);


    /**
     * 字典编码查询字典数据列表
     *
     * @param dictCode 字典编码
     * @return list
     */
    List<Tree<Long>> getByDictCode(String dictCode);


    /**
     * 查询字典数据列表
     *
     * @param dictCode     字典code
     * @param parentDataId 父数据ID
     * @return vo
     */
    List<Tree<Long>> getListByDictCodeParentDataId(String dictCode, Long parentDataId);

    /**
     * 分页查询ID
     *
     * @param pageDTO dto
     * @return pageVO
     */
    PageVO<DictDataVO> page(DictDataPageDTO pageDTO);


    /**
     * 通过dictCode删除字典数据
     *
     * @param dictCode 字典编号
     */
    void removeByDictCode(String dictCode);


}
