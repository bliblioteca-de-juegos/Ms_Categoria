package com.biblioteca.Ms_Categoria.controller;

import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/categorias")
@Tag(name = "Categorias", description = "Operaciones de categorias de juegos")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // GET http://localhost:8081/api/v2/categorias -> 200 OK
    @GetMapping
    @Operation(summary = "Listar todas las categorias")
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.ObternerTodas());
    }

    // GET http://localhost:8081/api/v2/categorias/{id} -> 200 OK o 404
    // ms-Juegos valida por HTTP que una categoria exista antes de guardar un juego.
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoria por ID")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8081/api/v2/categorias -> 201 Created
    @PostMapping
    @Operation(summary = "Crear una categoria")
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaService.guardar(categoria));
    }

    // PUT http://localhost:8081/api/v2/categorias/{id} -> 200 OK o 404
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria datos) {
        return categoriaService.obtenerPorId(id)
                .map(existente -> {
                    datos.setId(id);
                    return ResponseEntity.ok(categoriaService.guardar(datos));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE http://localhost:8081/api/v2/categorias/{id} -> 204 No Content o 404
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoria")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
