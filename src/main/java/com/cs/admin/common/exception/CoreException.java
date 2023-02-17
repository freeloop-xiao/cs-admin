package com.cs.admin.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author xiao kun
 * @version 1.0
 * @since 2019-10-28 14:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoreException extends RuntimeException {

    private Integer status;


    public static void throwException(String message) {
        throw new CoreException(message);
    }

    public CoreException() {
    }

    public CoreException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST.value();
    }

    public CoreException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}