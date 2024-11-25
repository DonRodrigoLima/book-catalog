package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        // Validações (se necessário) para os campos adicionais
        if (livro.getTitulo() == null || livro.getEditora() == null || livro.getAnoPublicacao() == null) {
            throw new IllegalArgumentException("Os campos título, editora e ano de publicação são obrigatórios.");
        }
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deletarPorId(Long id) {
        livroRepository.deleteById(id);
    }
}
