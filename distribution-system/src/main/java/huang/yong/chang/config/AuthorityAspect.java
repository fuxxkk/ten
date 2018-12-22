package huang.yong.chang.config;

import huang.yong.chang.entity.Role;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.util.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 权限拦截
 */
@Aspect
@Slf4j
@Component
@Order(100)
public class AuthorityAspect {

    @Value("${authority.url}")
    private String[] urls;

    @Pointcut(value = "execution(* huang.yong.chang.controller..*.*(..))")
    public void checkAuth() {

    }

    @Before(value = "checkAuth()")
    public void beforeLog(JoinPoint joinPoint) throws SystemException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Boolean isAdmin = false;
        List<Role> roles = ContextUtils.getUser().getRoles();
        for (Role role : roles) {
            if (role.getRoleName().equalsIgnoreCase("admin")) {
                isAdmin = true;
                break;
            }
        }

        if (!isAdmin) {
            String requestURI = request.getRequestURI();
            for (String url : urls) {
                if (requestURI.contains(url)) {
                    throw new SystemException("该用户无权限访问");
                }
            }
        }
    }

}
