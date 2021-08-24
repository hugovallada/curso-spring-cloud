package com.github.hugovallada.greetingservice.controller;

import com.github.hugovallada.greetingservice.configuration.GreetingConfiguration;
import com.github.hugovallada.greetingservice.model.Greeting;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
@Log4j2
public class GreetingController {

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;

    @GetMapping
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "")
            String name
            ){
        log.info("Greeting api received a GET request");

        if(name.isEmpty()) name = configuration.getDefaultValue();

        return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
    }

}
