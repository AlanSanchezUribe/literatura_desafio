package com.literatura.desafio.principal;

import java.util.Optional;
import java.util.Scanner;

import org.hibernate.mapping.List;

import com.literatura.desafio.modelo.Autor;
import com.literatura.desafio.modelo.DatosAPI;
import com.literatura.desafio.modelo.DatosLibros;
import com.literatura.desafio.modelo.Libro;
import com.literatura.desafio.repository.AutorRepository;
import com.literatura.desafio.repository.LibroRepository;
import com.literatura.desafio.service.ConsumoAPI;
import com.literatura.desafio.service.ConvierteDatos;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI api = new ConsumoAPI();
    private ConvierteDatos convierte = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal (LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }


    public void mostrarMenu() {

var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                 case 2:
                    mostrarLibros();
                    break;

                case 3:
                    mostrarAutores();
                    break;

                 case 4:
                    buscarAutoresVivos();
                    break;

                 case 5:
                    mostrarLibrosPorIdioma();
                    break; 


                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
                 System.out.println("Ingrese el nombre del libro a buscar");
        var nombreLibro = scanner.nextLine();
        var json = api.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ", "+"));
        DatosAPI datosBusqueda = convierte.obtenerDatos(json, DatosAPI.class);

        

         Optional<DatosLibros> libro = datosBusqueda.resultados().stream()
            .filter(l -> l.title().toLowerCase().contains(nombreLibro.toLowerCase()))
            .findFirst();

        if(libro.isPresent()) {
            var libroEncontrado = libro.get();
            var libroGuardado = repositorio.findByTitleIgnoreCase(libroEncontrado.title());
           


            if(libroGuardado == null ) {
                var nuevoLibro = new Libro(libroEncontrado);
                
                repositorio.save(nuevoLibro);
                
                System.out.println(nuevoLibro);
                System.out.println("------Libro guardado-------" + "\n");
            

           
                
            } else {
                System.out.println("\n" + "El libro ya existe se encuentra en nuestros registros" + "\n" );
            }
        } else {
            System.out.println("\n" + "No se encontro el libro" + "\n");
        } 
    } 
        
        private void mostrarLibros() {
            var libros = repositorio.findAll();
            libros.stream()
                .forEach(System.out::println);

        }

        private void mostrarAutores() {
            var autores = autorRepository.findAll();
            autores.stream()
                .forEach(System.out::println);
        }


        private void buscarAutoresVivos() {
            System.out.println("Ingrese un año");
            var year = scanner.nextInt();
            scanner.nextLine();

            var autores = autorRepository.autoresVivos(year);
            autores.stream()
                .forEach(System.out::println);
            
        }

        private void mostrarLibrosPorIdioma() {
            System.out.println("Ingrese el idioma");
            var idioma = scanner.nextLine();

            var libros = repositorio.findByLanguage(idioma);
            libros.stream()
                .forEach(System.out::println);
        }
            
        }


        


          









       
    

    
