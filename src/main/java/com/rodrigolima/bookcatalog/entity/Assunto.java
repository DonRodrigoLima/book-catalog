package com.rodrigolima.bookcatalog.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    public Assunto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros;
}

