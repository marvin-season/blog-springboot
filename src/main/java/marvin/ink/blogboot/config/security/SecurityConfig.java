package marvin.ink.blogboot.config.security;

import marvin.ink.blogboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    //  权限继承
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MyAuthenticationSuccessHandler successHandler;

    @Autowired
    MyAuthenticationEntryPoint authenticationEntryPointHandler;

    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;


    private final String[] allowUrls = {
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/**",
            "/doc.html",
            "/user/login",
            "/user/forget-password",
            "/user/verification-code",
            "/user/captcha",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(allowUrls).permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .successHandler(successHandler) // json 返回
                .failureHandler(authenticationFailureHandler)
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) // 角色无权限
                .authenticationEntryPoint(authenticationEntryPointHandler) // 未认证

                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
