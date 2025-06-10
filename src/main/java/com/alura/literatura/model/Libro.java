package com.alura.literatura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    String titulo;

    private String autor;

    private String idioma;
    Double numeroDeDescargas;

    //public serie
}

