package com.kdanwoo.authserver.controller;

import com.kdanwoo.authserver.entity.Book;
import com.kdanwoo.authserver.entity.User;
import com.kdanwoo.authserver.repository.UserRepository;
import com.kdanwoo.authserver.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserRepository userJpaRepo;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping
    public List<Book> all(){
        return bookService.findAll();
    }

    @RequestMapping("/test")
    public void insert(){
        userJpaRepo.save(User.builder()
                .uid("k.danwoo91@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .name("kdanwoo")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

}
