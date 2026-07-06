package com.biblioteca.Ms_Categoria.config;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;
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
