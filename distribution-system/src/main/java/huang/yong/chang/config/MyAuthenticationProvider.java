package huang.yong.chang.config;

import huang.yong.chang.entity.User;
import huang.yong.chang.service.UserService;
import huang.yong.chang.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputName = authentication.getName();
        String inputPwd = (String) authentication.getCredentials();

        User user = userService.findByUsername(inputName);
        if (user==null||!user.getPassword().equals(MD5Util.encode(inputPwd))) {
            throw new DisabledException("账号密码错误");
        }

        return new UsernamePasswordAuthenticationToken(user,inputPwd,user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
