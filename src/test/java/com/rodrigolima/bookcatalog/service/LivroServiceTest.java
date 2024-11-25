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

        Livro livro = new Livro(
            null,
            "Livro Teste",
            "123-456",
            29.90,
            Set.of(new Autor(1L, "Autor 1")),
            Set.of(new Assunto(1L, "Assunto 1"))
        );

        when(autorRepository.existsById(1L)).thenReturn(true);
        when(assuntoRepository.existsById(1L)).thenReturn(true);
        when(livroRepository.save(any(Livro.class))).thenReturn(
            new Livro(1L, "Livro Teste", "123-456", 29.90, null, null)
        );

   
        Livro livroSalvo = livroService.salvar(livro);


        verify(autorRepository, times(1)).existsById(1L);
        verify(assuntoRepository, times(1)).existsById(1L);
        verify(livroRepository, times(1)).save(livro);
    }
}
