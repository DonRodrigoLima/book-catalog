package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.repository.AssuntoRepository;
import com.rodrigolima.bookcatalog.repository.AutorRepository;
import com.rodrigolima.bookcatalog.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AssuntoRepository assuntoRepository;

    @InjectMocks
    private LivroService livroService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

@Test
public void deveSalvarLivro() {
    Livro livro = new Livro(1L, "Título Exemplo", 29.90, "Editora Exemplo", 1, "2024", null, null);
    when(livroRepository.save(any(Livro.class))).thenReturn(livro);

    Livro livroSalvo = livroService.salvar(livro);

    assertEquals("Título Exemplo", livroSalvo.getTitulo());
    assertEquals("Editora Exemplo", livroSalvo.getEditora());
    assertEquals("2024", livroSalvo.getAnoPublicacao());
}

}
