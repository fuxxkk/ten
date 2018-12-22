package huang.yong.chang.config;

import com.alibaba.fastjson.JSON;
import huang.yong.chang.entity.Role;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.util.ContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SwaggerIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Boolean isAdmin = false;
        List<Role> roles = ContextUtils.getUser().getRoles();
        for (Role role : roles) {
            if (role.getRoleName().equalsIgnoreCase("admin")) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("该用户无权限访问");
            return false;
        }
        return true;
    }
}
