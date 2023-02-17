package com.cs.admin.common.exception;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.cs.admin.common.vo.ErrorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 全局异常捕获
 *
 * @author : free loop
 * @since : 2019-09-23
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 全局异常捕获
     *
     * @param e exception
     * @return ResponseErrorVO
     */
    @ExceptionHandler(value = CoreException.class)
    public ResponseEntity<ErrorVO> coreExceptionHandler(CoreException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        return ResponseEntity.status(e.getStatus()).body(ErrorVO.error(e.getStatus(), e.getMessage()));
    }


    /**
     * 请求参数验证异常捕获
     *
     * @param e 异常
     * @return 异常署吗
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorVO> validExceptionHandler(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ExceptionUtil.stacktraceToString(e));
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        Map<String, String> errMsg = new HashMap<>();
        for (ObjectError error : errors) {
            String[] str = Objects.requireNonNull(error.getCodes())[1].split("\\.");
            String message = error.getDefaultMessage();
            errMsg.put(str[1], message);
        }
        return ResponseEntity.unprocessableEntity().body(ErrorVO.error(HttpStatus.UNPROCESSABLE_ENTITY.value(), errMsg));
    }

    /**
     * 客户端全局异常捕捉处理
     * 捕获 Filter 异常
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorVO> handleFilterException(Exception e) {
        log.error("HttpMessageNotReadableException :{}", e.getMessage());
        return ResponseEntity.badRequest().body(ErrorVO.error(HttpStatus.BAD_REQUEST.value(), "请求参数json格式错误!"));
    }

    /**
     * 客户端全局异常捕捉处理
     * 捕获 Filter 异常
     */
    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<ErrorVO> handleIoException(IOException e) {
        log.error("IOException :{}", e.getMessage());
        return ResponseEntity.badRequest().body(ErrorVO.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ErrorVO> handleAccessDeniedException(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorVO.error(HttpStatus.FORBIDDEN.value(), "无访问权限"));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorVO> handleException(Exception e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        log.error("Exception :{}", e.getMessage());
        return ResponseEntity.badRequest().body(ErrorVO.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }


}