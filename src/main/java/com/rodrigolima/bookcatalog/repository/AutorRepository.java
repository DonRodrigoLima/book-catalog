package com.rodrigolima.bookcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigolima.bookcatalog.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
