package com.cs.admin.system.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.admin.common.util.PageUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.dict.domain.covert.SysDictDataConvert;
import com.cs.admin.system.dict.domain.dto.DictDataAddDTO;
import com.cs.admin.system.dict.domain.dto.DictDataEditDTO;
import com.cs.admin.system.dict.domain.dto.DictDataPageDTO;
import com.cs.admin.system.dict.domain.entity.SysDictData;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import com.cs.admin.system.dict.domain.vo.DictDataVO;
import com.cs.admin.system.dict.mapper.SysDictDataMapper;
import com.cs.admin.system.dict.service.SysDictDataService;
import com.cs.admin.system.dict.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Autowired
    private SysDictDataConvert sysDictDataConvert;

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(DictDataAddDTO addDTO) {
        checkAdd(addDTO);
        SysDictData dictData = sysDictDataConvert.toEntity(addDTO);
        dictData.setCreateBy(WebUtil.getUserId());
        return save(dictData);
    }

    @Override
    public Boolean del(Long dataId) {
        return removeById(dataId);
    }

    @Override
    public Boolean edit(DictDataEditDTO editDTO) {
        checkEdit(editDTO);
        SysDictData dictData = sysDictDataConvert.toEntity(editDTO);
        dictData.setUpdateBy(WebUtil.getUserId());
        return updateById(dictData);
    }

    @Override
    public DictDataVO getVO(Long dataId) {
        return sysDictDataConvert.toVO(getById(dataId));
    }

    @Override
    public List<Tree<Long>> getByDictCode(String dictCode) {

        if (StrUtil.isEmpty(dictCode)) {
            return null;
        }

        List<DictDataVO> list = sysDictDataConvert.toVO(getBaseMapper().selectByDictCode(dictCode));

        return buildDictData(list, 0L);
    }

    @Override
    public List<Tree<Long>> getListByDictCodeParentDataId(String dictCode, Long parentDataId) {

        if (ObjectUtil.isNull(parentDataId)) {
            parentDataId = 0L;
        }

        List<DictDataVO> list = sysDictDataConvert.toVO(getBaseMapper().selectByDictCodeAndParentDataId(dictCode, parentDataId));

        return buildDictData(list, parentDataId);
    }

    @Override
    public PageVO<DictDataVO> page(DictDataPageDTO pageDTO) {

        Page<SysDictData> pageCondition = PageUtil.page(pageDTO);

        QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(pageDTO.getDictCode())) {
            queryWrapper.eq("dict_code", pageDTO.getDictCode());
        }

        if (ObjectUtil.isNotNull(pageDTO.getIsDefault())) {
            queryWrapper.eq("is_default", pageDTO.getIsDefault());
        }

        if (ObjectUtil.isNotNull(pageDTO.getParentDataId())) {
            queryWrapper.eq("parent_data_id", pageDTO.getParentDataId());
        }

        if (ObjectUtil.isNotNull(pageDTO.getStatus())) {
            queryWrapper.eq("status", pageDTO.getStatus());
        }

        if (StrUtil.isNotEmpty(pageDTO.getKey())) {
            queryWrapper.and(x -> x.like("dict_label", pageDTO.getKey())
                    .or().like("dict_value", pageDTO.getKey()));
        }

        Page<SysDictData> result = page(pageCondition, queryWrapper);

        return PageVO.toVO(result.getCurrent(), result.getSize(),
                result.getTotal(), sysDictDataConvert.toVO(result.getRecords()));
    }


    @Override
    public void removeByDictCode(String dictCode) {
        if (StrUtil.isNotEmpty(dictCode)) {
            getBaseMapper().deleteByDictCode(dictCode);
        }
    }


    private void checkAdd(DictDataAddDTO addDTO) {
        checkDataId(addDTO.getParentDataId());
        checkDictCode(addDTO.getDictCode());
    }


    private SysDictData checkDataId(Long dataId) {

        if (ObjectUtil.isNull(dataId) || dataId == 0L) {
            return null;
        }
        SysDictData dictData = getById(dataId);
        if (ObjectUtil.isNull(dictData)) {
            ReportUtil.throwEx(StrUtil.format("字典数据ID[{}]不存在!", dataId));
        }

        return dictData;
    }


    private void checkDictCode(String dictCode) {

        SysDictType dictType = sysDictTypeService.getByDictCode(dictCode);
        if (ObjectUtil.isNull(dictType)) {
            ReportUtil.throwEx(StrUtil.format("字典编码[{}]不存在!", dictCode));
        }
    }

    private void checkEdit(DictDataEditDTO editDTO) {

        if (editDTO.getDataId().equals(editDTO.getParentDataId())) {
            ReportUtil.throwEx(StrUtil.format("字典数据父ID[{}]不合法!", editDTO.getParentDataId()));
        }

        SysDictData dictData = checkDataId(editDTO.getDataId());

        // 如果父字典ID修改需要校验
        assert dictData != null;
        if (!dictData.getParentDataId().equals(editDTO.getParentDataId())) {
            checkDataId(editDTO.getParentDataId());
        }

        // 如果修改字典code则需要校验
        if (!dictData.getDictCode().equals(editDTO.getDictCode())) {
            checkDictCode(editDTO.getDictCode());
        }
    }

    private TreeNode<Long> mapToTreeNode(DictDataVO dictData) {
        TreeNode<Long> treeNode = new TreeNode<>(dictData.getDataId(), dictData.getParentDataId(), dictData.getDictCode(), dictData.getDictSort());
        Map<String, Object> map = BeanUtil.beanToMap(dictData, false, true);
        map.remove("dataId");
        map.remove("parentDataId");
        map.remove("dictSort");
        treeNode.setExtra(map);
        return treeNode;
    }

    private List<Tree<Long>> buildDictData(List<DictDataVO> list, Long parentDataId) {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("dictSort");
        treeNodeConfig.setIdKey("dataId");
        treeNodeConfig.setParentIdKey("parentDataId");
        treeNodeConfig.setNameKey("name");
        treeNodeConfig.setDeep(50);
        List<TreeNode<Long>> treeNodes = list.stream().map(this::mapToTreeNode).collect(Collectors.toList());
        return TreeUtil.build(treeNodes, parentDataId, treeNodeConfig, new DefaultNodeParser<>());
    }


}
