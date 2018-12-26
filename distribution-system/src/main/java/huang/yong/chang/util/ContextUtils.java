package huang.yong.chang.util;

import huang.yong.chang.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ContextUtils {

    public final static String SESSIONNAME = "session_user";

    public static User getUser() {
        return (User) getSession().getAttribute(SESSIONNAME);
    }

    public static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getSession();
    }

    public static void setUser(User user) {
        HttpSession session = getSession();
        session.setAttribute(SESSIONNAME, user);
    }
}
