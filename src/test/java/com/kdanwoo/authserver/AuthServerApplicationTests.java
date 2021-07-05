package com.kdanwoo.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthServerApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("testSecret : " + passwordEncoder.encode("testSecret")));
    }
}
