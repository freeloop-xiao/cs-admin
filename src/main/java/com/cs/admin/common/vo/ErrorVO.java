package com.cs.admin.common.vo;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 错误响应信息主体
 *
 * @author : free loop
 * @since : 2019-09-23
 */
@Data
public class ErrorVO implements Serializable {

    @ApiModelProperty(value = "http状态码")
    private Integer status;

    @ApiModelProperty(value = "响应时间")
    private String timestamp;

    @ApiModelProperty(value = "错误说明")
    private Object message;

    public static ErrorVO error(Object message) {
        ErrorVO error = new ErrorVO();
        error.status = HttpStatus.BAD_REQUEST.value();
        error.setTimestamp(LocalDateTime.now().format(DatePattern.NORM_DATETIME_FORMATTER));
        error.setMessage(message);
        return error;
    }

    public static ErrorVO error(Integer status, Object message) {
        ErrorVO error = new ErrorVO();
        error.setStatus(status);
        error.setMessage(message);
        error.setTimestamp(LocalDateTime.now().format(DatePattern.NORM_DATETIME_FORMATTER));
        return error;
    }

}
