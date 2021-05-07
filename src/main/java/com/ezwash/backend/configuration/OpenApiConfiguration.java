package com.ezwash.backend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "backendOpenApi")
    public OpenAPI backendOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("Backend Application API")
                .description("Backend API implemented with Sprint Boot RESTfull service and documeted using springdoc-openapi and OpenAPI 3.0"));
    }
}
