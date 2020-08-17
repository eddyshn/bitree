package com.eddy.security01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //password get from Security01ApplicationTests
        auth.inMemoryAuthentication()
                .withUser("eddy").password("$2a$10$qgkM16ida5xiY9muaKx7YOA5v4Fz4yCmRPFDvBhd7UlxcenNVcXT2").roles("admin")
                .and()
                .withUser("eddy2").password("$2a$10$zc2q4lV482NZeehGaOiRputlunyqWsQapJtZM1kIgDiMCn6XIWtIG").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasAnyAuthority("admin", "user")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")//点击登陆按钮时访问的URL
                .loginPage("/login")//登陆页面
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
