package com.biblioteca.Ms_Categoria.hateoas;

import com.biblioteca.Ms_Categoria.model.Categoria;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaModelAssemblerTest {

    private final Faker faker = new Faker();

    @Test
    void agregaEnlacesALaCategoriaYSusJuegos() {
        CategoriaModelAssembler assembler = new CategoriaModelAssembler("http://localhost:8082");
        Categoria categoria = new Categoria(5L, faker.lorem().word(), faker.lorem().sentence());

        EntityModel<Categoria> modelo = assembler.toModel(categoria);

        assertTrue(modelo.hasLink("self"));
        assertTrue(modelo.hasLink("categorias"));
        assertEquals(
                "http://localhost:8082/api/v2/hateoas/juegos/categoria/5",
                modelo.getRequiredLink("juegos").getHref()
        );
    }
}
