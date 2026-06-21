package com.biblioteca.Ms_Categoria.service;

import com.biblioteca.Ms_Categoria.model.Categoria;
import com.biblioteca.Ms_Categoria.repository.CategoriaRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    private CategoriaService categoriaService;

    private final Faker faker = new Faker();

    @Test
    void guardarRetornaLaCategoriaPersistida() {
        Categoria categoria = new Categoria(
                1L,
                faker.lorem().word(),
                faker.lorem().sentence()
        );
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria resultado = categoriaService.guardar(categoria);

        assertEquals(categoria.getNombre(), resultado.getNombre());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void obtenerPorIdRetornaVacioCuandoLaCategoriaNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        assertFalse(categoriaService.obtenerPorId(id).isPresent());
    }
}
