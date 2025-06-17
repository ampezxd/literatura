# 📚 Literatura

Este proyecto en Java permite consumir y manipular datos literarios desde la API publica de Gutendex  y aplicar filtros sobre los resultados, como listar libros por idioma utilizando **JPQL**.

## 🚀 Objetivo

El propósito del proyecto es practicar el consumo de APIs REST, transformar los datos obtenidos y almacenarlos en una base de datos utilizando **JPA**. A su vez, se experimenta con consultas JPQL para recuperar información útil, como la lista de libros según el idioma en que están escritos.

## 🛠️ Tecnologías utilizadas

- Java 17+
- Maven
- JPA (Jakarta Persistence API)
- JPQL (Java Persistence Query Language)
- Gson 
- API externa: [Gutendex](https://gutendex.com/)

- ## ✅ Funcionalidades

- ✅ Consumir API de libros (Gutendex).
- ✅ Convertir respuestas JSON a objetos Java.
- ✅ Filtrar libros por idioma utilizando JPQL.
- ✅ Persistir libros y autores en una base de datos relacional con JPA.

- ## 🛢️ Base de datos

Se utiliza una base de datos relacional para almacenar libros y autores, gestionada con JPA (Jakarta Persistence API).

- Tipo de relación: Muchos a Muchos (ManyToMany) entre `Libro` y `Autor`.
- La base de datos se configura a través de `application.properties` o `application.yml`.



