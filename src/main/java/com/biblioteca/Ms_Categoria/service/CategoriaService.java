package com.biblioteca.Ms_Categoria.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> ObternerTodas(){ return categoriaRepository.findAll();}
    public Optional<Categoria> obtenerPorId(Long id){
        return categoriaRepository.findById(id);
    }
    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public void eliminar(Long id){
        categoriaRepository.deleteById(id);
    }
}
