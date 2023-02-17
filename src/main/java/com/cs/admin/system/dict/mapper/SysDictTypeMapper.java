package com.cs.admin.system.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs.admin.system.dict.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2021-01-26
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 字典名称查询字典类型
     *
     * @param dictName 字典名称
     * @return dictType
     */
    SysDictType selectByDictName(@Param("dictName") String dictName);

    /**
     * 字典code查询字典类型
     *
     * @param dictCode code
     * @return dictType
     */
    SysDictType selectByDictCode(@Param("dictCode") String dictCode);

}
