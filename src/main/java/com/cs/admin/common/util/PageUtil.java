package com.cs.admin.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.admin.common.dto.PageDTO;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页查询转换工具
 *
 * @author free loop
 * @version 1.0
 * @since 2019-10-12 12:36
 */

public class PageUtil {


    /**
     * 分页查询转换成条件page
     *
     * @param pageDTO 分页查询条件传输对象
     * @param <T>     查询对象
     * @return 查询对象
     */
    public static <T> Page<T> page(PageDTO pageDTO) {

        if (ObjectUtil.isNull(pageDTO.getPage())) {
            pageDTO.setPage(1L);
        }

        if (ObjectUtil.isNull(pageDTO.getLimit())) {
            pageDTO.setLimit(10L);
        }

        Page<T> page = new Page<>(pageDTO.getPage(), pageDTO.getLimit());
        if (StringUtils.hasText(pageDTO.getOrderAscBy())) {
            page.addOrder(OrderItem.desc(FormatParam.toUnderline(pageDTO.getOrderAscBy())));
        } else if (StringUtils.hasText(pageDTO.getOrderDescBy())) {
            page.addOrder(OrderItem.desc(FormatParam.toUnderline(pageDTO.getOrderDescBy())));
        } else {
            page.addOrder(OrderItem.desc("create_time"));
        }
        return page;
    }

    /**
     * 分页查询结果转换目标类型
     *
     * @param result   分页查询结果
     * @param function 转换函数
     * @param <T>      查询返回类型
     * @param <R>      转换目标类型
     * @return Page<R>
     */
    public static <T, R> Page<R> toTarget(Page<T> result, Function<T, R> function) {
        Page<R> target = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        target.setRecords(result.getRecords().stream().map(function).collect(Collectors.toList()));
        return target;
    }


    /**
     * Map转换为对应查询对象
     *
     * @param map 查询条件参数
     * @param <T> 查询类型
     * @return queryWraper
     */
    public static <T> QueryWrapper<T> mapToQueryWrapper(Map<Object, Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (map == null) {
            return null;
        }
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            queryWrapper.eq((String) entry.getKey(), entry.getValue());
        }
        return queryWrapper;
    }

}
