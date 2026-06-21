package com.biblioteca.Ms_Categoria.hateoas;

import com.biblioteca.Ms_Categoria.model.Categoria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoriaModelAssembler
        implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

    private final String juegosUrl;

    public CategoriaModelAssembler(@Value("${ms.juegos.url}") String juegosUrl) {
        this.juegosUrl = juegosUrl;
    }

    @Override
    public EntityModel<Categoria> toModel(Categoria categoria) {
        return EntityModel.of(
                categoria,
                linkTo(methodOn(CategoriaHateoasController.class).obtenerPorId(categoria.getId())).withSelfRel(),
                linkTo(methodOn(CategoriaHateoasController.class).obtenerTodas()).withRel("categorias"),
                Link.of(juegosUrl + "/api/v2/hateoas/juegos/categoria/" + categoria.getId(), "juegos")
        );
    }
}
