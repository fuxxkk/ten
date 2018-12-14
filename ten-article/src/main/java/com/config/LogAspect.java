package com.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Slf4j
@Component
public class LogAspect {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(* com.controller..*.*(..))")
    public void recordlog() {

    }

    @Before(value = "recordlog()")
    public void beforeLog(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String name = joinPoint.getSignature().getName();
        log.info("========httpmethod:"+request.getMethod()+"===="+"method："+name+",args:"+ Arrays.toString(joinPoint.getArgs())+"========");
    }

    @After(value = "recordlog()")
    public void doAfter() {
        log.info("================total time : {}",System.currentTimeMillis()-startTime.get()+"ms ===============");
    }

    /*@Around(value = "recordlog()")
    public Result aroundController(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = joinPoint.proceed();
        Result result = new Result(true, StatusCode.OK, "success!", proceed);
        return result;
    }*/
}
