package com.biblioteca.Ms_Categoria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI categoriaOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Ms-Categoria")
                .version("1.0")
                .description("Gestion de categorias de juegos."));
    }
}
