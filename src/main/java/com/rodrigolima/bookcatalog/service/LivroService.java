package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Livro;
import com.rodrigolima.bookcatalog.exception.LivroNotFoundException;
import com.rodrigolima.bookcatalog.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
    }

    public void deletarPorId(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException(id);
        }
        livroRepository.deleteById(id);
    }
}

