package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.service.AssuntoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssuntoController.class)
@AutoConfigureMockMvc(addFilters = false) // Desabilita filtros de segurança para os testes
public class AssuntoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssuntoService assuntoService;

    @Test
    public void deveListarTodosOsAssuntos() throws Exception {

        Assunto assunto1 = new Assunto(1L, "Assunto 1");
        Assunto assunto2 = new Assunto(2L, "Assunto 2");
        when(assuntoService.listarTodos()).thenReturn(Arrays.asList(assunto1, assunto2));

        mockMvc.perform(get("/api/assuntos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Assunto 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].descricao").value("Assunto 2"));

        Mockito.verify(assuntoService, Mockito.times(1)).listarTodos();
    }

    @Test
    public void deveCriarAssunto() throws Exception {

        Assunto assunto = new Assunto(1L, "Assunto 1");
        when(assuntoService.salvar(Mockito.any(Assunto.class))).thenReturn(assunto);


        mockMvc.perform(post("/api/assuntos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\": \"Assunto 1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Assunto 1"));

        Mockito.verify(assuntoService, Mockito.times(1)).salvar(Mockito.any(Assunto.class));
    }
}

