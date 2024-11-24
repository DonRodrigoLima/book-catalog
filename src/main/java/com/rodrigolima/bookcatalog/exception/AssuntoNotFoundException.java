package com.rodrigolima.bookcatalog.exception;

public class AssuntoNotFoundException extends ResourceNotFoundException {

    public AssuntoNotFoundException(Long id) {
        super("Assunto não encontrado com ID: " + id);
    }
}

