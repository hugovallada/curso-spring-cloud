package com.github.hugovallada.cambioservice.controller;

import com.github.hugovallada.cambioservice.model.Cambio;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Log4j2
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

    @GetMapping("{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    ){
        log.info("Cambio API received a request to get the cambio");
        return new Cambio(1L, from, to, BigDecimal.ONE, BigDecimal.ONE, "PORT 8000");
    }


}
