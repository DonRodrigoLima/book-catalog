package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.repository.AssuntoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssuntoService {

    private final AssuntoRepository assuntoRepository;

    public Assunto salvar(Assunto assunto) {
        return assuntoRepository.save(assunto);
    }

    public List<Assunto> listarTodos() {
        return assuntoRepository.findAll();
    }
}

