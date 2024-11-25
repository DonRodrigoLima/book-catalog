package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.service.LivroService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Test
    public void deveListarTodosOsLivros() throws Exception {

        Livro livro1 = new Livro(1L, "Livro 1", "123-456", 29.90, null, null);
        Livro livro2 = new Livro(2L, "Livro 2", "789-101", 49.90, null, null);
        when(livroService.listarTodos()).thenReturn(Arrays.asList(livro1, livro2));

 
        mockMvc.perform(get("/api/livros")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Livro 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Livro 2"));

        Mockito.verify(livroService, Mockito.times(1)).listarTodos();
    }

    @Test
    public void deveCriarLivro() throws Exception {

        Livro livro = new Livro(1L, "Livro 1", "123-456", 29.90, null, null);
        when(livroService.salvar(Mockito.any(Livro.class))).thenReturn(livro);


        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\": \"Livro 1\", \"isbn\": \"123-456\", \"valor\": 29.90}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Livro 1"))
                .andExpect(jsonPath("$.isbn").value("123-456"))
                .andExpect(jsonPath("$.valor").value(29.90));

        Mockito.verify(livroService, Mockito.times(1)).salvar(Mockito.any(Livro.class));
    }
}

