package com.rodrigolima.bookcatalog.exception;

public class AutorNotFoundException extends ResourceNotFoundException {

    public AutorNotFoundException(Long id) {
        super("Autor não encontrado com ID: " + id);
    }
}

