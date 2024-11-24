package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarLivro() {
        // Arrange
        Livro livro = new Livro(
            null,
            "Livro de Teste",
            "123-456-789",
            49.90,
            Set.of(new Autor(1L, "João Silva")),
            Set.of(new Assunto(1L, "Tecnologia"))
        );

        Livro livroSalvo = new Livro(
            1L,
            "Livro de Teste",
            "123-456-789",
            49.90,
            Set.of(new Autor(1L, "João Silva")),
            Set.of(new Assunto(1L, "Tecnologia"))
        );

        when(livroRepository.save(any(Livro.class))).thenReturn(livroSalvo);

        // Act
        Livro resultado = livroService.salvar(livro);

        // Assert
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getTitulo()).isEqualTo("Livro de Teste");
        assertThat(resultado.getAutores()).hasSize(1);
        assertThat(resultado.getAssuntos()).hasSize(1);

        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    void deveListarTodosOsLivros() {
     
        when(livroRepository.findAll()).thenReturn(
            List.of(
                new Livro(1L, "Livro A", "111-111", 29.90, Set.of(), Set.of()),
                new Livro(2L, "Livro B", "222-222", 39.90, Set.of(), Set.of())
            )
        );

       
        var livros = livroService.listarTodos();

      
        assertThat(livros).hasSize(2);
        verify(livroRepository, times(1)).findAll();
    }
}
