package com.cs.admin.system.dict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.covert.SysDictTypeConvert;
import com.cs.admin.system.dict.domain.dto.DictTypeAddDTO;
import com.cs.admin.system.dict.domain.dto.DictTypeEditDTO;
import com.cs.admin.system.dict.domain.dto.DictTypePageDTO;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictTypeVO;
import com.cs.admin.system.dict.mapper.SysDictTypeMapper;
import com.cs.admin.system.dict.service.SysDictDataService;
import com.cs.admin.system.dict.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeConvert sysDictTypeConvert;

    @Autowired
    private SysDictDataService sysDictDataService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(DictTypeAddDTO addDTO) {

        checkAdd(addDTO);

        SysDictType dictType = sysDictTypeConvert.toEntity(addDTO);
        dictType.setCreateBy(WebUtil.getUserId());
        return save(dictType);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean del(Long dictId) {

        SysDictType dictType = checkDictId(dictId);

        sysDictDataService.removeByDictCode(dictType.getDictCode());

        return removeById(dictId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean edit(DictTypeEditDTO editDTO) {
        checkEdit(editDTO);
        SysDictType dictType = sysDictTypeConvert.toEntity(editDTO);
        dictType.setUpdateBy(WebUtil.getUserId());
        return updateById(dictType);
    }

    @Override
    public DictTypeVO getVO(Long dictId) {
        DictTypeVO typeVO = sysDictTypeConvert.toVO(getById(dictId));
        typeVO.setData(sysDictDataService.getByDictCode(typeVO.getDictCode()));
        return typeVO;
    }

    @Override
    public PageVO<DictTypeVO> page(DictTypePageDTO pageDTO) {

        Page<SysDictType> pageCondition = PageUtil.page(pageDTO);
        QueryWrapper<SysDictType> queryWrapper = new QueryWrapper<>();

        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("dict_code", pageDTO.getKey())
                    .or().like("dict_name", pageDTO.getKey()));
        }

        Page<SysDictType> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysDictTypeConvert.toVO(result.getRecords()));
    }


    @Override
    public SysDictType getByDictCode(String dictCode) {
        return getBaseMapper().selectByDictCode(dictCode);
    }

    /**
     * 新增字典类型验证
     *
     * @param addDTO dto
     */
    private void checkAdd(DictTypeAddDTO addDTO) {

        SysDictType dictType = getBaseMapper().selectByDictCode(addDTO.getDictCode());

        if (ObjectUtil.isNotNull(dictType)) {
            ReportUtil.throwEx(StrUtil.format("字典类型编码[{}]已存在!", addDTO.getDictCode()));
        }

        dictType = getBaseMapper().selectByDictName(addDTO.getDictName());

        if (ObjectUtil.isNotNull(dictType)) {
            ReportUtil.throwEx(StrUtil.format("字典名称[{}]已存在!", addDTO.getDictName()));
        }
    }

    /**
     * 验证dictId是否负载
     *
     * @param dictId 字典ID
     * @return sysDictType
     */
    private SysDictType checkDictId(Long dictId) {

        SysDictType dictType = getById(dictId);

        if (ObjectUtil.isNull(dictType)) {
            ReportUtil.throwEx(StrUtil.format("字典ID[{}]不存在!", dictId));
        }

        return dictType;
    }


    /**
     * 修改字典前验证
     *
     * @param editDTO dto
     */
    private void checkEdit(DictTypeEditDTO editDTO) {

        checkDictId(editDTO.getDictId());

        SysDictType dictType = getBaseMapper().selectByDictCode(editDTO.getDictCode());

        // 如果dictCode查询字典类型和提交上来的dictId不相等则不能发生变更
        if (ObjectUtil.isNotNull(dictType) &&
                !dictType.getDictId().equals(editDTO.getDictId())) {
            ReportUtil.throwEx(StrUtil.format("字典编码[{}]已存在,请更换编码!", editDTO.getDictCode()));
        }

        // 如果dictName查询字典类型和提交上来的dictId不相等则不能发生变更
        dictType = getBaseMapper().selectByDictName(editDTO.getDictName());
        if (ObjectUtil.isNotNull(dictType) &&
                !dictType.getDictId().equals(editDTO.getDictId())) {
            ReportUtil.throwEx(StrUtil.format("字典名称[{}]已存在,请更换字典名称!", editDTO.getDictName()));
        }
    }


}
