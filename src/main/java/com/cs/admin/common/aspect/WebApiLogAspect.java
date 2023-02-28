package com.cs.admin.common.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.cs.admin.common.util.RequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *
 * Swagger注释接口调用日志
 *
 * 用于开发测试记录请求响应报文测试
 *
 * @author free loop
 * @version 1.0
 * @since 2020/8/14 23:31
 */
@Slf4j
@Aspect
@Component
@Profile({"dev", "test", "local"})
public class WebApiLogAspect {

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void webProfileLog() {
    }


    /**
     * 在切点之前织入
     *
     * @param joinPoint 连接点
     * @throws Throwable 异常
     */
    @Before("webProfileLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = null;
        if (ObjectUtil.isNotNull(authentication) && authentication.getPrincipal() instanceof Long) {
            userId = (Long) authentication.getPrincipal();
        }

        // 获取 @ApiOperation 注解的描述信息
        String methodDescription = getApiOperationNodes(joinPoint);

        // 打印请求相关参数
        log.info("=================== Start ==========================================");
        // 打印请求 url
        log.info("URL           : {}", request.getRequestURL().toString());
        log.info("UserId        : {}", userId);
        // 打印描述信息
        log.info("Description   : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method   : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method  : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP            : {}", RequestUtil.getIp(request));

        // 打印请求入参,处理文件下载类型
        log.info("Request Args  : {}", JSON.toJSONString(RequestUtil.getRequestParam(joinPoint.getArgs())));

    }


    /**
     * 在切点之后织入
     */
    @After("webProfileLog()")
    public void doAfter() {
        // 接口结束后换行，方便分割查看
        log.info("=================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint 连接点
     * @return result
     * @throws Throwable 异常
     */
    @Around("webProfileLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            // 打印出参
            log.info("Response Args  : {}", JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.info("Response Args Exception : {}", e.getMessage());
            throw e;
        } finally {
            log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        }
    }


    private static String getApiOperationNodes(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (ObjectUtil.isNotNull(apiOperation)) {
            return apiOperation.notes();
        }
        return null;
    }


}
