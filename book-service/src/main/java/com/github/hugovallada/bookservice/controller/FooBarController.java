package com.github.hugovallada.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    //@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") # tenta x numeros de vezes antes de ir pro fallback
    //@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod") # tenta chamar, mas se tiver muitos acessos ele passa a desviar o fluxo pro fallback
    //@RateLimiter(name = "default") // define qnts requisições o método pode receber
    @Bulkhead(name = "default")
    public String fooBar(){
        log.info("Request to foo-bar is received");
//        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Foo-Bar";
    }

    public String fallbackMethod(Exception ex){ // deve ter um exception
        log.info("Fallback called");
        return "Fallback Method Foo-Bar";
    }


}
