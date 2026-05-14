package com.biblioteca.Ms_Categoria.config;

import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args){
        if (categoriaRepository.count() > 0){
            log.info(">>> Ms_categoria: BD ya tiene datos, se omite la carga inicial");
            return;
        }
        categoriaRepository.save(new Categoria(null, "AAA",  "Juego de alto presupuesto"));
        categoriaRepository.save(new Categoria(null, "AA","juego de bajo presupuesto"));
        categoriaRepository.save(new Categoria(null, "indi","juego desarrollado por una empresa pequeña"));
        log.info(">>> Ms_categorias: {} categorías insertadas.", categoriaRepository.count());
    }

}
