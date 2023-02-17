package com.cs.admin.common.mapstruct;

import java.util.List;

/**
 * <p>
 * map struct 框架转换器描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/22 20:54
 */
public interface BaseConvert<D, E, V> {

    /**
     * DTO转Entity
     *
     * @param dto source
     * @return target
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity source
     * @return target
     */
    V toVO(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList source
     * @return target
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList source
     * @return target
     */
    List<V> toVO(List<E> entityList);
}
