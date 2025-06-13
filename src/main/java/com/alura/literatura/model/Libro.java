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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor", //Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id"), //Clave de Libro
            inverseJoinColumns = @JoinColumn(name = "autor_id") //Clave de Autor
    )
    private List<Autor> autores;

    public Libro(){

    }
    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autor = datosLibros.autor() != null && !datosLibros.autor().isEmpty() ? datosLibros.autor().get(0).nombre()
                : "Desconocido";
        this.idioma = datosLibros.idiomas() != null && !datosLibros.idiomas().isEmpty() ? datosLibros.idiomas().get(0)
                : "Desconocido";
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return " titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}

