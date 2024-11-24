package com.rodrigolima.bookcatalog.service;

import com.rodrigolima.bookcatalog.entity.Assunto;
import com.rodrigolima.bookcatalog.exception.AssuntoNotFoundException;
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
    
    public Assunto buscarPorId(Long id) {
        return assuntoRepository.findById(id)
                .orElseThrow(() -> new AssuntoNotFoundException(id));
    }

    public void deletarPorId(Long id) {
        if (!assuntoRepository.existsById(id)) {
            throw new AssuntoNotFoundException(id);
        }
        assuntoRepository.deleteById(id);
    }
}

