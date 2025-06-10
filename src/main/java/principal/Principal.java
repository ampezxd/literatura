package principal;

import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosAutor;
import com.alura.literatura.model.DatosLibros;
import service.ConsumoAPI;
import service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<DatosLibros> librosBuscados = new ArrayList<>();
    private List<DatosAutor> autoresBuscados = new ArrayList<>();
    List<String> idiomas = new ArrayList<String>();

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datos);

        var opcion = -1;
        while(opcion != 0) {
            var menu = """
                    1-Buscar libro por título
                    2-Listar libros registrados
                    3-Listar autores registrados
                    4-Listar autores vivos en un determinado año
                    5-Listar libros por idioma
                    0-Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEnUnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    //Buscar el libro mediante la API
    private DatosLibros buscarLibro() {
        System.out.println("Digite el título del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        if (nombreLibro.isBlank()){
            System.out.println("El título no puede estar vacío.");
            return null;
        }else {
            try {
                var json = ConsumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
                System.out.println(json);
                Datos datos = conversor.obtenerDatos(json, Datos.class);
                DatosAutor datosAutor = conversor.obtenerDatos(json, DatosAutor.class);
                if (datos.resultados() != null){
                    for (DatosLibros libro : datos.resultados()){
                        boolean yaExiste = librosBuscados.stream()
                                .anyMatch(l -> l.titulo().equalsIgnoreCase(libro.titulo()));
                        if (!yaExiste) {
                            librosBuscados.add(libro);
                            idiomas.addAll(libro.idiomas());
                            autoresBuscados.addAll(libro.autor());
                        }
                    }
                }
            }catch (Exception e) {
                System.out.println("Error al obtener o procesar los datos del libro: " + e.getMessage());
            }
        }
        return null;
    }
    //Listar libros buscados
    private void listarLibros() {
        if (librosBuscados.isEmpty()) {
            System.out.println("No hay libros en la lista.");
            return;
        }

        for (DatosLibros libro : librosBuscados) {
            System.out.println("Título: " + libro.titulo());

            System.out.println("Idiomas: " + libro.idiomas());
            System.out.println("Número de descargas: " + libro.numeroDeDescargas());
            System.out.println("---------------");
        }
    }
    private void listarAutores() {
        autoresBuscados.forEach(System.out::println);
    }
    private void listarAutoresVivosEnUnAnio(){
        System.out.println("Digite el año por el que desea buscar el autor: ");
        Integer autorAnio = teclado.nextInt();
        autoresBuscados.stream()
                .filter(autoresBuscados -> autoresBuscados.getClass().getYear()==autorAnio);
    }
    private void listarLibrosPorIdioma() {
        idiomas.forEach(System.out::println);

    }
}
