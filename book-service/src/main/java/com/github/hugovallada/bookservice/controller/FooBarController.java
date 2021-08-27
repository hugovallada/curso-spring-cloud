package com.github.hugovallada.bookservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bar")
public class FooBarController {

    @GetMapping("/foo-bar")
    public String fooBar(){
        return "Foo-Bar";
    }


}
