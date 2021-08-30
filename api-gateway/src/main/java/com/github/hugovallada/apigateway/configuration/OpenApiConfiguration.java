package com.github.hugovallada.apigateway.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    @Lazy(false) // Tem q ser carregado qnd o gateway estiver iniciando
    public List<GroupedOpenApi> apis (SwaggerUiConfigParameters s, RouteDefinitionLocator r) {

        var definitions = r.getRouteDefinitions().collectList().block();

        definitions.stream().filter(route -> route.getId().matches(".*-service")).forEach(routeDefinition -> {
            String name = routeDefinition.getId();
            s.addGroup(name);
            GroupedOpenApi.builder()
                    .pathsToMatch("/" + name + "/**").group(name).build();
        });

        return new ArrayList<>();
    }

}
