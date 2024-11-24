package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.repository.AssuntoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

class AssuntoServiceTest {

    @Mock
    private AssuntoRepository assuntoRepository;

    @InjectMocks
    private AssuntoService assuntoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarAssunto() {
      
        Assunto assunto = new Assunto(null, "Tecnologia");
        Assunto assuntoSalvo = new Assunto(1L, "Tecnologia");

        when(assuntoRepository.save(any(Assunto.class))).thenReturn(assuntoSalvo);

      
        Assunto resultado = assuntoService.salvar(assunto);

       
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getDescricao()).isEqualTo("Tecnologia");

        verify(assuntoRepository, times(1)).save(assunto);
    }

    @Test
    void deveListarTodosOsAssuntos() {
      
        when(assuntoRepository.findAll()).thenReturn(
            List.of(
                new Assunto(1L, "Tecnologia"),
                new Assunto(2L, "Ficção")
            )
        );

   
        var assuntos = assuntoService.listarTodos();

     
        assertThat(assuntos).hasSize(2);
        assertThat(assuntos.get(0).getDescricao()).isEqualTo("Tecnologia");
        assertThat(assuntos.get(1).getDescricao()).isEqualTo("Ficção");

        verify(assuntoRepository, times(1)).findAll();
    }
}

