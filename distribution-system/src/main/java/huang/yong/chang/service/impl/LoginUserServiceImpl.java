package huang.yong.chang.service.impl;

import huang.yong.chang.entity.User;
import huang.yong.chang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        Optional.of(user).orElseThrow(() -> new UsernameNotFoundException("用户密码错误"));
        System.out.println("用户已登录："+user);
        return user;
    }
}
