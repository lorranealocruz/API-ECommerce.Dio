package br.com.ecommerce.api_ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API E-Commerce")
                .version("v1.0")
                .description("Projeto final de API para um sistema de e-commerce.")
                .termsOfService("http://swagger.io/terms/") 
                .license(new License()
                         .name("Apache 2.0")
                         .url("http://springdoc.org")
                )
            );
    }
}