package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.service.RelatorioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RelatorioController.class)
@AutoConfigureMockMvc(addFilters = false) // Desabilita filtros de segurança para os testes
public class RelatorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RelatorioService relatorioService;

    @Test
    public void deveGerarRelatorioLivros() throws Exception {

        byte[] mockPdf = new byte[]{1, 2, 3}; // Mock do conteúdo do PDF
        when(relatorioService.gerarRelatorioLivros()).thenReturn(mockPdf);


        mockMvc.perform(get("/api/relatorios/livros")
                .accept(MediaType.APPLICATION_PDF))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andExpect(header().string("Content-Disposition", "inline; filename=RelatorioLivros.pdf"))
                .andExpect(content().bytes(mockPdf));

        // Verificar se o serviço foi chamado uma vez
        Mockito.verify(relatorioService, Mockito.times(1)).gerarRelatorioLivros();
    }
}

