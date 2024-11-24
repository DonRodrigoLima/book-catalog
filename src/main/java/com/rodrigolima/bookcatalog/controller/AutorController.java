package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
@Tag(name = "Autores", description = "Gerenciamento de autores")
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    @Operation(summary = "Criar um novo autor", description = "Endpoint para criar um novo autor no sistema.")
    public ResponseEntity<Autor> criarAutor(@RequestBody Autor autor) {
        return ResponseEntity.ok(autorService.salvar(autor));
    }

    @GetMapping
    @Operation(summary = "Listar todos os autores", description = "Endpoint para obter a lista de todos os autores cadastrados no sistema.")
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Endpoint para buscar um autor específico pelo seu ID.")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar autor por ID", description = "Endpoint para atualizar as informações de um autor existente pelo seu ID.")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        autor.setId(id);
        return ResponseEntity.ok(autorService.salvar(autor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar autor por ID", description = "Endpoint para remover um autor do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        autorService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
