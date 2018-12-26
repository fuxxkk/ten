/*
package huang.yong.chang.config;

import com.alibaba.fastjson.JSON;
import huang.yong.chang.base.Result;
import huang.yong.chang.base.StatusCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Boolean isAdmin = false;
        */
/*Collection<? extends GrantedAuthority> authorities = ContextUtils.getUser().getAuthorities();
        for (GrantedAuthority auth : authorities) {
            String authority = auth.getAuthority();
            if (authority.equalsIgnoreCase("admin")) {
                isAdmin = true;
                break;
            }
        }*//*


        if (!isAdmin) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            Result result = new Result(false, StatusCode.ACCESSERROR, "该用户无权限访问", null);
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}
*/
