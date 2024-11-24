package com.rodrigolima.bookcatalog.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.exception.AutorNotFoundException;
import com.rodrigolima.bookcatalog.repository.AutorRepository;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new AutorNotFoundException(id));
    }

    public void deletarPorId(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new AutorNotFoundException(id);
        }
        autorRepository.deleteById(id);
    }
}

