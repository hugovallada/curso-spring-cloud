package com.github.hugovallada.greetingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Greeting {

    private final long id;

    private final String content;

}
