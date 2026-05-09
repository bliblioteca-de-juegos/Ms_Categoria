package com.biblioteca.Ms_Categoria.controller;

import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // GET http://localhost:8081/api/categorias → 200 OK
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.ObternerTodas());
    }

    // GET http://localhost:8081/api/categorias/{id} → 200 OK o 404
    // ms-libros llama a este endpoint con RestTemplate para validar
    // que una categoría existe antes de guardar un libro.
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8081/api/categorias → 201 Created
    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaService.guardar(categoria));
    }

    // PUT http://localhost:8081/api/categorias/{id} → 200 OK o 404
    @PutMapping("/{id}")
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

    // DELETE http://localhost:8081/api/categorias/{id} → 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
