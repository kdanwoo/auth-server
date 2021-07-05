package com.kdanwoo.authserver.controller;

import com.kdanwoo.authserver.entity.Book;
import com.kdanwoo.authserver.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @RequestMapping
    public List<Book> all(){
        return bookService.findAll();
    }


}
