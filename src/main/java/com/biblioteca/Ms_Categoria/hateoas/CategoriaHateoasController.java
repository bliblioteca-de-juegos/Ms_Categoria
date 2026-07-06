package com.biblioteca.Ms_Categoria.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/v2/hateoas/categorias")
@Tag(name = "Categorias HATEOAS", description = "Consulta de categorias con enlaces de navegacion")
public class CategoriaHateoasController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaModelAssembler assembler;
    @GetMapping
    @Operation(summary = "Listar categorias con enlaces HATEOAS")
    public CollectionModel<EntityModel<Categoria>> obtenerTodas() {
        List<EntityModel<Categoria>> categorias = categoriaService.ObternerTodas().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(
                categorias,
                linkTo(methodOn(CategoriaHateoasController.class).obtenerTodas()).withSelfRel()
        );
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoria con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Categoria>> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
