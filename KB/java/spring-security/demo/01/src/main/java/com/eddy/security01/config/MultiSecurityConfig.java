package com.eddy.security01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
