package com.example.springdemo.Aspect;

import cn.hutool.core.util.ObjectUtil;
import com.example.springdemo.Interface.ControllerWebLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@Order(100)
public class WebLogAspect {

    //private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * 横切点
     */
    @Pointcut("execution(public * com.example.springdemo.Controller.*.*.*(..))")
    public void webLog() {
    }
    /**
     * 接收请求，并记录数据
     * @param joinPoint
     * @param controllerWebLog
     */
    @Before(value = "webLog()&& @annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog) {
        // 接收到请求
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 记录请求内容，threadInfo存储所有内容
        Map<String, Object> threadInfo = new HashMap<>();
        log.info("URL : " + request.getRequestURL());
        threadInfo.put("url", request.getRequestURL());
        log.info("URI : " + request.getRequestURI());
        threadInfo.put("uri", request.getRequestURI());
        log.info("HTTP_METHOD : " + request.getMethod());
        threadInfo.put("httpMethod", request.getMethod());
        log.info("REMOTE_ADDR : " + request.getRemoteAddr());
        threadInfo.put("ip", request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        threadInfo.put("classMethod",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        threadInfo.put("args", Arrays.toString(joinPoint.getArgs()));
        log.info("USER_AGENT："+request.getHeader("User-Agent"));
        threadInfo.put("userAgent", request.getHeader("User-Agent"));
        log.info("执行方法：" + controllerWebLog.name());
        threadInfo.put("methodName", controllerWebLog.name());
        threadLocal.set(threadInfo);
    }
    /**
     * 执行成功后处理
     * @param controllerWebLog
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(value = "webLog()&& @annotation(controllerWebLog)", returning = "ret")
    public void doAfterReturning(ControllerWebLog controllerWebLog, Object ret) throws Throwable {
        Map<String, Object> threadInfo = threadLocal.get();
        threadInfo.put("result", ret);
        if (controllerWebLog.intoDb()) {
            //插入数据库操作
            //insertResult(threadInfo);
        }
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }
    /**
     * 获取执行时间
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = proceedingJoinPoint.proceed();
        Map<String, Object> threadInfo = threadLocal.get();
        if (!ObjectUtil.isEmpty(threadInfo)){
            Long takeTime = System.currentTimeMillis() - startTime;
            threadInfo.put("takeTime", takeTime);
            log.info("耗时：" + takeTime);
        }
        threadLocal.set(threadInfo);
        return ob;
    }
    /**
     * 异常处理
     * @param throwable
     */
    @AfterThrowing(value = "webLog()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();

        ServletRequestAttributes sra = (ServletRequestAttributes) ra;

        HttpServletRequest request = sra.getRequest();
        // 异常信息
        log.error("{}接口调用异常，异常信息{}", request.getRequestURI(), throwable);
    }

}

