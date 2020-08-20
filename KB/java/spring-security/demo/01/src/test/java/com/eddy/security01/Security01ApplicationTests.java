package com.eddy.security01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Security01ApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        System.out.println(encode.encode("123"));
        System.out.println(encode.encode("123"));
        //$2a$10$qgkM16ida5xiY9muaKx7YOA5v4Fz4yCmRPFDvBhd7UlxcenNVcXT2
        //$2a$10$zc2q4lV482NZeehGaOiRputlunyqWsQapJtZM1kIgDiMCn6XIWtIG
    }

}
