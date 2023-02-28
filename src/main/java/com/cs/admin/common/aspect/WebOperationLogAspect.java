package com.cs.admin.common.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.cs.admin.common.annotation.WebLog;
import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.util.RequestUtil;
import com.cs.admin.common.util.WebUtil;
import com.cs.admin.system.log.domain.entity.SysLog;
import com.cs.admin.system.log.service.SysLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static com.cs.admin.common.util.RequestUtil.getBrowser;
import static com.cs.admin.common.util.RequestUtil.getIp;

/**
 * 操作日志切面
 * 可用于操作日志功能
 *
 * @author free loop
 * @version 1.0
 * @since 2020/8/14 23:31
 */
@Slf4j
@Aspect
//@Component
@AllArgsConstructor
public class WebOperationLogAspect {

    private final SysLogService logService;

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.cs.admin.common.annotation.WebLog)")
    public void webLog() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint 连接点
     * @return result
     * @throws Throwable 异常
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        String error = null;
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            error = ExceptionUtil.stacktraceToString(e);
            throw e;
        } finally {
            SysLog sysLog = getInitLog(startTime, error, proceedingJoinPoint);
            logService.add(sysLog);
            WebUtil.removeOnlineInfo();
        }
    }


    private SysLog getInitLog(long startTime, String error, ProceedingJoinPoint proceedingJoinPoint) {

        // 获取@WebLog注解信息
        String description = getWebLogValue(proceedingJoinPoint);

        // 获取请求属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (ObjectUtil.isNull(description) || ObjectUtil.isNull(attributes)) {
            return null;
        }

        // 开始打印请求日志
        HttpServletRequest request = attributes.getRequest();
        SysLog sysLog = new SysLog();

        // 设置日志描叙
        sysLog.setDescription(description);
        // 请求方法记录
        sysLog.setMethod(request.getMethod());
        // 设置请求路径
        sysLog.setPath(request.getServletPath());
        // 请求参数
        sysLog.setParams(JSON.toJSONString(RequestUtil.getRequestParam(proceedingJoinPoint.getArgs())));
        // 请求ip地址
        sysLog.setRequestIp(getIp(request));
        // 请求浏览器
        sysLog.setBrowser(getBrowser(request));

        // 如果异常记录异常stack
        if (ObjectUtil.isNotNull(error)) {
            sysLog.setLogStatus(1);
            sysLog.setExceptionDetail(error);
        }
        // 记录耗时
        sysLog.setTime(System.currentTimeMillis() - startTime);

        // 设置用户信息
        OnlineInfoDTO onlineInfo = WebUtil.getOnlineInfo();
        if (ObjectUtil.isNotNull(onlineInfo)) {

            // 设置用户账户
            sysLog.setAccount(onlineInfo.getAccount());

            // 设置用户id
            sysLog.setUserId(onlineInfo.getUserId());
        }

        return sysLog;
    }

    /**
     * 获取日志描叙
     *
     * @param proceedingJoinPoint 连接点
     * @return description
     */
    private static String getWebLogValue(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        WebLog webLog = method.getAnnotation(WebLog.class);
        if (ObjectUtil.isNotNull(webLog)) {
            return webLog.value();
        }
        return null;
    }


}
