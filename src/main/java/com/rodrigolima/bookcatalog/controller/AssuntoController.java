package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.service.AssuntoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assuntos")
@RequiredArgsConstructor
@Tag(name = "Assuntos", description = "Gerenciamento de assuntos")
public class AssuntoController {

    private final AssuntoService assuntoService;

    @PostMapping
    @Operation(summary = "Criar um novo assunto", description = "Endpoint para criar um novo assunto no sistema.")
    public ResponseEntity<Assunto> criarAssunto(@RequestBody Assunto assunto) {
        return ResponseEntity.ok(assuntoService.salvar(assunto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os assuntos", description = "Endpoint para obter a lista de todos os assuntos cadastrados no sistema.")
    public ResponseEntity<List<Assunto>> listarAssuntos() {
        return ResponseEntity.ok(assuntoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar assunto por ID", description = "Endpoint para buscar um assunto específico pelo seu ID.")
    public ResponseEntity<Assunto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assuntoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar assunto por ID", description = "Endpoint para atualizar as informações de um assunto existente pelo seu ID.")
    public ResponseEntity<Assunto> atualizarAssunto(@PathVariable Long id, @RequestBody Assunto assunto) {
        assunto.setId(id);
        return ResponseEntity.ok(assuntoService.salvar(assunto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar assunto por ID", description = "Endpoint para remover um assunto do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarAssunto(@PathVariable Long id) {
        assuntoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
