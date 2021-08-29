package com.github.hugovallada.bookservice.controller;

import com.github.hugovallada.bookservice.api.proxy.CambioProxy;
import com.github.hugovallada.bookservice.model.Book;
import com.github.hugovallada.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/books")
@Log4j2
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy cambioProxy;

    @Operation(summary = "Find specific book by id")
    @SneakyThrows
    @GetMapping("{id}/{currency}")
    public Book getBook(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        log.info("Book API received a request");

        var book = repository.getById(id);

        if (book == null) throw new Exception("Book can't be null");

        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());

        return book;
    }

}
