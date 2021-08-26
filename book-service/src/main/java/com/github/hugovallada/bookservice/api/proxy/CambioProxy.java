package com.github.hugovallada.bookservice.api.proxy;

import com.github.hugovallada.bookservice.api.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "cambio-service") // com o Eureka isso já é suficiente
public interface CambioProxy {

    @GetMapping("/cambio-service/{amount}/{from}/{to}")
    Cambio getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    );
}
