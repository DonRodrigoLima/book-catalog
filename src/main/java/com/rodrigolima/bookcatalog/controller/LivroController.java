package com.rodrigolima.bookcatalog.controller;

import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
@Tag(name = "Livros", description = "Gerenciamento de livros")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    @Operation(summary = "Criar um novo livro", description = "Endpoint para criar um novo livro no sistema.")
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(livroService.salvar(livro));
    }

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Endpoint para obter a lista de todos os livros cadastrados no sistema.")
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Endpoint para buscar um livro específico pelo seu ID.")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro por ID", description = "Endpoint para atualizar as informações de um livro existente pelo seu ID.")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        return ResponseEntity.ok(livroService.salvar(livro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro por ID", description = "Endpoint para remover um livro do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
