package com.github.hugovallada.greetingservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
@RefreshScope
@ConfigurationProperties("greeting-service") // classe q faz o bind entre o properties e a classe java
public class GreetingConfiguration {

    private String greeting;

    private String defaultValue;

}
