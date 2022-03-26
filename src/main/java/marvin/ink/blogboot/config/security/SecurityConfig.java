package marvin.ink.blogboot.config.security;

import marvin.ink.blogboot.filter.JWTAuthenticationFilter;
import marvin.ink.blogboot.filter.TokenServiceFilter;
import marvin.ink.blogboot.handler.MyAccessDeniedHandler;
import marvin.ink.blogboot.handler.MyAuthenticationEntryPointHandler;
import marvin.ink.blogboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService userService;

    @Autowired
    private TokenServiceFilter tokenServiceFilter;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    //  权限继承
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Autowired
    MyAuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;

    private final String[] allowUrls = {
            "/imserver/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/**",
            "/doc.html",
            "/login",
            "/user/registry",
            "/user/forget-password",
            "/captcha/**",
            "/static/**",
            "/index.html"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(allowUrls).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                // 异常处理
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) // 角色无权限
                .authenticationEntryPoint(authenticationEntryPointHandler) // 未认证访问
                // 过滤器
                .and()
                .addFilter(tokenServiceFilter)
                .addFilterAfter(jwtAuthenticationFilter, TokenServiceFilter.class)
                .csrf().disable(); // 跨站请求伪造
        // 跨域
        http.cors().configurationSource(CorsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    private CorsConfigurationSource CorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //  同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，POST、GET等
        source.registerCorsConfiguration("/**", corsConfiguration); //配置允许跨域访问的url
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
