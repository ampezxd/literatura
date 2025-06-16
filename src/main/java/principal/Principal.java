package principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import service.ConsumoAPI;
import service.ConvierteDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<DatosLibros> librosBuscados = new ArrayList<>();
    private List<Autor> autoresBuscados = new ArrayList<Autor>();
    List<String> idiomas = new ArrayList<String>();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

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
    private DatosLibros getDatosLibros() {
        System.out.println("Escribe el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();

        var json = ConsumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json,Datos.class); //Para primero mapear al cotenedor
        if (datos.resultados().isEmpty()){
            System.out.println("No se encontraron libros con ese título.");
            return null;
        }
        return datos.resultados().get(0); //Obtiene solo el primer libro
    }
    private DatosLibros buscarLibro() {
        DatosLibros datos = getDatosLibros();
        if (datos == null) return null;
        Libro libro = new Libro(datos);
        List<Autor> autores = datos.autor().stream()
                        .map(a -> new Autor(a.nombre(),a))
                        .collect(Collectors.toList());

        libro.setAutores(autores);
        autores.forEach(a ->a.getLibro().add(libro));
        repositorio.save(libro);
        autoresBuscados.addAll(autores);
        System.out.println(datos);
        return datos;
    }
    //Listar libros buscados
    private void listarLibros() {
        List<Libro> libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    private void listarAutores() {
       List<Autor> autores = autorRepository.findAll(); //Busca todos los autores en la base de datos
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
            return;
        }
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }
    private void listarAutoresVivosEnUnAnio(){
        System.out.println("Digite el año por el que desea buscar el autor: ");
        Integer fechaABuscar = teclado.nextInt();
        List<Autor> busquedaDeAutor = autorRepository.autoresPorAnio(fechaABuscar);
        System.out.println("***Autores vivos en el año: " + fechaABuscar);
        busquedaDeAutor.forEach(a ->
                System.out.println(a.getNombre() + " nacio en el año: " + a.getFechaDeNacimiento() + " y fallecio en el año: " + a.getFechaDeFallecimiento()));
    }
    private void listarLibrosPorIdioma() {
        System.out.println("Escoja el número que hace referencía al idioma del cual quiere buscar libros");
        System.out.println("""
                1.-Español: digite >>es<<
                2.-Ingles: digite >>en<<
                """);
        String opcionIdioma = teclado.nextLine();
        List<Libro> buscarIdioma = repositorio.idiomaBuscado(opcionIdioma);
        System.out.println("Libros en el idioma que escogio:");
        buscarIdioma.forEach(i ->
                System.out.println(i.getTitulo() + i.getIdioma()));
        System.out.println("*******************************************************************");


    }
}
