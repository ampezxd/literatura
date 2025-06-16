package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT i FROM Libro i WHERE i.idioma = :opcionIdioma")
    List<Libro> idiomaBuscado(String opcionIdioma);
}
 // SELECT * FROM libros WHERE idioma = 'en';
//@Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fechaABuscar AND a.fechaDeFallecimiento >= :fechaABuscar")
//    List<Autor> autoresPorAnio(Integer fechaABuscar);