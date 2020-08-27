package com.eddy.security01.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MultiSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("eddy3").password("$2a$10$G3kVAJHvmRrr6sOj.j4xpO2Dsxl5EG8rHycPHFWyi9UMIhtdSH15u").roles("admin")
                .and()
                .withUser("eddy4").password("$2a$10$kWjG2GxWhm/2tN2ZBpi7bexXjUneIKFxIAaMYJzY7WcziZLCD4PZS").roles("user");
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");
        }
    }

    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/doLogin")
                    .loginPage("/login")//登陆页面
                    .usernameParameter("uname")
                    .passwordParameter("passwd")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status-multi", 200);
                            map.put("msg-multi", authentication.getPrincipal());
                            out.write(new ObjectMapper().writeValueAsString(map));
                            out.flush();
                            out.close();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status-multi", 401);

                            if (e instanceof LockedException){
                                map.put("msg-multi", "账户锁定");
                            } else if (e instanceof BadCredentialsException){
                                map.put("msg-multi", "用户名或密码错误");
                            } else  if (e instanceof DisabledException){
                                map.put("msg-multi", "账户禁用");
                            } else if (e instanceof AccountExpiredException){

                            } else if (e instanceof CredentialsExpiredException){

                            } else {
                                map.put("msg-multi", "登陆失败");
                            }

                            out.write(new ObjectMapper().writeValueAsString(map));
                            out.flush();
                            out.close();
                        }
                    })
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status-multi", 200);
                            map.put("msg-multi", "注销成功");
                            out.write(new ObjectMapper().writeValueAsString(map));
                            out.flush();
                            out.close();
                        }
                    })
                    .and()
                    .csrf().disable();
        }
    }
}
