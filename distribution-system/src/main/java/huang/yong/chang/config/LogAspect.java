package huang.yong.chang.config;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.User;
import huang.yong.chang.util.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Slf4j
@Component
@Order(101)
public class LogAspect {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(* huang.yong.chang.controller..*.*(..))")
    public void recordlog() {

    }

    @Before(value = "recordlog()")
    public void beforeLog(JoinPoint joinPoint) {
        User user = ContextUtils.getUser();
        String username = "";
        if (user != null) {
            username = user.getUsername();

        }
        startTime.set(System.currentTimeMillis());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String name = joinPoint.getSignature().getName();
        log.info("用户：{}========httpmethod:"+request.getMethod()+"===="+"method："+name+",args:"+ Arrays.toString(joinPoint.getArgs())+"========",username);
    }

    @AfterReturning(value = "recordlog()",returning = "result")
    public void doAfter(JoinPoint point,Object result) {
        long totalTime = System.currentTimeMillis() - startTime.get();
        if (result instanceof Result) {
            Result target = (Result)result;
            target.setTotalTime(totalTime);
        }
        log.info("================total time : {}", totalTime +"ms ===============");
    }

    /*@Around(value = "recordlog()")
    public Result aroundController(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = joinPoint.proceed();
        Result result = new Result(true, StatusCode.OK, "success!", proceed);
        return result;
    }*/
}
