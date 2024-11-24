package com.rodrigolima.bookcatalog.repository;

import com.rodrigolima.bookcatalog.entity.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoRepository extends JpaRepository<Assunto, Long> {}

