package com.rodrigolima.bookcatalog.repository;

import com.rodrigolima.bookcatalog.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}

