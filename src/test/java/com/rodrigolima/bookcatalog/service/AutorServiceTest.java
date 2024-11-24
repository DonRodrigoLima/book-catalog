package com.rodrigolima.bookcatalog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.repository.AutorRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarAutor() {
        Autor autor = new Autor(null, "João Silva");
        Autor autorSalvo = new Autor(1L, "João Silva");
        when(autorRepository.save(any(Autor.class))).thenReturn(autorSalvo);

        Autor resultado = autorService.salvar(autor);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("João Silva");
        verify(autorRepository, times(1)).save(autor);
    }
}