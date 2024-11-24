package com.rodrigolima.bookcatalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.rodrigolima.bookcatalog.entity.Autor;
import com.rodrigolima.bookcatalog.repository.AutorRepository;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }
}

