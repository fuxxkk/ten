package huang.yong.chang.config;

import huang.yong.chang.entity.Role;
import huang.yong.chang.entity.User;
import huang.yong.chang.service.RoleService;
import huang.yong.chang.service.UserService;
import huang.yong.chang.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputName = authentication.getName();
        String inputPwd = (String) authentication.getCredentials();

        User user = userService.findByUsername(inputName);
        if (user==null||!user.getPassword().equals(MD5Util.encode(inputPwd))) {
            log.info("用户：{} ，{}尝试登陆，账号密码错误。",inputName,MD5Util.encode(inputPwd));
            throw new DisabledException("账号密码错误");
        }
        log.info("用户：{} 登陆成功",user);
        List<Role> roles = roleService.findByUserId(user.getId());
        user.setRoles(roles);
        return new UsernamePasswordAuthenticationToken(user,inputPwd,user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
