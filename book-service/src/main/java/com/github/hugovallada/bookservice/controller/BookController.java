package com.github.hugovallada.bookservice.controller;

import com.github.hugovallada.bookservice.model.Book;
import com.github.hugovallada.bookservice.repository.BookRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/books")
@Log4j2
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @SneakyThrows
    @GetMapping("{id}/{currency}")
    public Book getBook(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        log.info("Book API received a request");

        var book = repository.getById(id);

        if(book == null) throw new Exception("Book can't be null");

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);

        return book;
    }

}
