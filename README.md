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


## ▶️ Modo de uso

Una vez ejecutada la aplicación, se mostrará un menú interactivo en consola con las siguientes opciones:
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
0 - Salir

### Ejemplo:

1. Selecciona la opción **1** para buscar un libro por título.  
2. Escribe el nombre del libro (por ejemplo: `Pride and Prejudice`).  
3. El sistema buscará el libro en la API de Gutendex, lo mostrará en consola y lo almacenará en la base de datos junto con su(s) autor(es).

### Notas:
- La opción **4** requiere ingresar un año para filtrar autores vivos en esa fecha.
- La opción **5** te pide el código de idioma (`es` para español, `en` para inglés) y lista los libros en ese idioma registrados en la base de datos.
