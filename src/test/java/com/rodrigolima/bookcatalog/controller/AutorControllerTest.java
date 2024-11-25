package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.service.AutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutorController.class)
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @Test
    public void deveListarTodosOsAutores() throws Exception {

        Autor autor1 = new Autor(1L, "Autor 1");
        Autor autor2 = new Autor(2L, "Autor 2");
        when(autorService.listarTodos()).thenReturn(Arrays.asList(autor1, autor2));

        mockMvc.perform(get("/api/autores")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Autor 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Autor 2"));

        // Verificar se o serviço foi chamado uma vez
        Mockito.verify(autorService, Mockito.times(1)).listarTodos();
    }
}

