package com.eddy.security01.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public class MultiSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //password get from Security01ApplicationTests
        auth.inMemoryAuthentication()
                .withUser("eddy").password("$2a$10$qgkM16ida5xiY9muaKx7YOA5v4Fz4yCmRPFDvBhd7UlxcenNVcXT2").roles("admin")
                .and()
                .withUser("eddy2").password("$2a$10$zc2q4lV482NZeehGaOiRputlunyqWsQapJtZM1kIgDiMCn6XIWtIG").roles("user");
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/admin/**").hasRole("admin");
        }
    }

    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/doLogin")//点击登陆按钮时访问的URL,postman隥陆URL
                    .loginPage("/login")//登陆页面
                    .usernameParameter("uname")
                    .passwordParameter("passwd")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status-other", 200);
                            map.put("msg-other", authentication.getPrincipal());
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
                            map.put("status-other", 401);

                            if (e instanceof LockedException){
                                map.put("msg-other", "账户锁定");
                            } else if (e instanceof BadCredentialsException){
                                map.put("msg-other", "用户名或密码错误");
                            } else  if (e instanceof DisabledException){
                                map.put("msg-other", "账户禁用");
                            } else if (e instanceof AccountExpiredException){

                            } else if (e instanceof CredentialsExpiredException){

                            } else {
                                map.put("msg-other", "登陆失败");
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
                            map.put("status-other", 200);
                            map.put("msg-other", "注销成功");
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
