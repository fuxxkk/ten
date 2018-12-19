package huang.yong.chang.config;

import huang.yong.chang.service.impl.LoginUserServiceImpl;
import huang.yong.chang.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;

/**
 * spring security配置类
 */

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public UserDetailsService loginUserService() {
        return new LoginUserServiceImpl();
    }

    /**
     * 自定义校验规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserService()).passwordEncoder(new PasswordEncoder() {
            /**
             * md5加密校对
             * @param charSequence
             * @return
             */
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encode((String) charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) charSequence));
            }
        });
    }

   /* @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin()
                .and()
                .csrf().disable();*/
        http.formLogin().defaultSuccessUrl("/swagger-ui.html",true)       //  定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest().authenticated()             // 任何请求,登录后可以访问
                .and()
                .csrf().disable();

        http.authorizeRequests().and().rememberMe().rememberMeServices(rememberMeServices()).key("INTERNAL_SECRET_KEY");
    }

   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/swagger-ui.html/**","/login");
    }*/

    @Bean
    public RememberMeServices rememberMeServices() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);

        PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices
                = new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", loginUserService(), repository);
        return persistentTokenBasedRememberMeServices;
    }
}
