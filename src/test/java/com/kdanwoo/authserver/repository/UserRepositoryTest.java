package com.kdanwoo.authserver.repository;

import com.kdanwoo.authserver.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert() {
        // given
        userJpaRepo.save(User.builder()
                .uid("k.danwoo91@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .name("kdanwoo")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}