package com.rodrigolima.bookcatalog.exception;

public class LivroNotFoundException extends ResourceNotFoundException {

    public LivroNotFoundException(Long id) {
        super("Livro não encontrado com ID: " + id);
    }
}

