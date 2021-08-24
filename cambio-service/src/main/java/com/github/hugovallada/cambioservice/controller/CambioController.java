package com.github.hugovallada.cambioservice.controller;

import com.github.hugovallada.cambioservice.model.Cambio;
import com.github.hugovallada.cambioservice.repository.CambioRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Log4j2
@RestController
@RequestMapping("/cambio-service")
@AllArgsConstructor
public class CambioController {

    private Environment environment;

    private CambioRepository repository;

    @GetMapping("{amount}/{from}/{to}")
    @SneakyThrows
    public Cambio getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    ){
        log.info("Cambio API received a request to get the cambio");
        var cambio = repository.findByFromAndTo(from, to);
        if(cambio == null) throw new Exception("Currency unsupported");

        var port = environment.getProperty("local.server.port");
        cambio.setEnvironment(port);

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        return new Cambio(1L, from, to, cambio.getConversionFactor(), cambio.getConvertedValue() , cambio.getEnvironment());
    }


}
