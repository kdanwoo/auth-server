package com.kdanwoo.authserver.service;

import com.kdanwoo.authserver.entity.Book;
import com.kdanwoo.authserver.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
