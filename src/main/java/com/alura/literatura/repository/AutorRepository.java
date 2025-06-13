package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fechaABuscar AND a.fechaDeFallecimiento >= :fechaABuscar")
    List<Autor> autoresPorAnio(Integer fechaABuscar);
}
