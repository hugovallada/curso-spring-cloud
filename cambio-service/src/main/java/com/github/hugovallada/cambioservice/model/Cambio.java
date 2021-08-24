package com.github.hugovallada.cambioservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cambio {

    private Long id;

    private String from;

    private String to;

    private BigDecimal conversionFactor;

    private BigDecimal convertedValue;

    private String environment;
}
