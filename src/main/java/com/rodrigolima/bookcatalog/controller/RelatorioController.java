package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
@Tag(name = "Relatórios", description = "Geração de relatórios do sistema")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/livros")
    @Operation(
        summary = "Gerar relatório de livros",
        description = "Endpoint para gerar um relatório consolidado com as informações dos livros cadastrados. "
                    + "Inclui título, autores, assuntos e outros detalhes."
    )
    public ResponseEntity<byte[]> gerarRelatorioLivros() throws JRException, SQLException {
        byte[] pdf = relatorioService.gerarRelatorioLivros();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=RelatorioLivros.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}


