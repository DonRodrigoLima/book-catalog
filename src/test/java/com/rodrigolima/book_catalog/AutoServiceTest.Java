package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    public AutorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    //Teste para verificar se esta salvando o Autor
    @Test
    void deveSalvarAutor() {
        
        Autor autor = new Autor(null, "João Silva");
        when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(1L, "João Silva"));

        
        Autor autorSalvo = autorService.salvar(autor);

        
        assertThat(autorSalvo.getId()).isNotNull();
        assertThat(autorSalvo.getNome()).isEqualTo("João Silva");
        verify(autorRepository, times(1)).save(autor);
    }
}
