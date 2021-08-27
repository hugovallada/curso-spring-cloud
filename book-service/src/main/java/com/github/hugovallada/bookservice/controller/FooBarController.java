package com.github.hugovallada.bookservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
@RequestMapping("/books")
public class FooBarController {


    @GetMapping("/foo-bar")
    @Retry(name = "foo-bar")
    public String fooBar(){
        log.info("Request to foo-bar is received");
        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }


}
