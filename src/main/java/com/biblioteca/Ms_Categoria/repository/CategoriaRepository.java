package com.biblioteca.Ms_Categoria.repository;
import com.biblioteca.Ms_Categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
