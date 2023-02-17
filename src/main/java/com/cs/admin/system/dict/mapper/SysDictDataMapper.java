package com.cs.admin.system.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.dict.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {


    /**
     * 字典编码查询列表
     *
     * @param dictCode 字典编码
     * @return list
     */
    List<SysDictData> selectByDictCode(@Param("dictCode") String dictCode);


    /**
     * 字典编码和父数据ID查询列表
     * @param dictCode  字典编码
     * @param parentDataId 父数据ID
     * @return list
     */
    List<SysDictData> selectByDictCodeAndParentDataId(@Param("dictCode") String dictCode,
                                                      @Param("parentDataId") Long parentDataId);


    /**
     * 删除字典数据
     *
     * @param dictCode 字典编码
     * @return int
     */
    Integer deleteByDictCode(@Param("dictCode") String dictCode);

}
