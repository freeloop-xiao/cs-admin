package com.cs.admin.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 分页返回统一pageVO说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/22 22:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页返回VO", description = "分页返回VO")
public class PageVO<T> {

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "每页显示条数，默认 10")
    private Long size;

    @ApiModelProperty(value = "当前页")
    private Long current;

    @ApiModelProperty(value = "当前页数据")
    private List<T> data;

    /**
     * 列表数据转换
     *
     * @param current 当前页码
     * @param size    每页显示条数
     * @param total   总记录数
     * @param data    记录列表
     * @param <T>     vo
     * @return pageVO
     */
    public static <T> PageVO<T> toVO(Long current, Long size, Long total, List<T> data) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setCurrent(current);
        pageVO.setSize(size);
        pageVO.setTotal(total);
        pageVO.setData(data);
        return pageVO;
    }

}
