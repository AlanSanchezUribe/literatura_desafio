package com.literatura.desafio.modelo;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne (cascade = CascadeType.ALL)
    private Autor author;
    private String language;
    private Double download_count;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.title = datosLibros.title();
        this.language = datosLibros.languages().get(0);
        this.download_count = datosLibros.download_count();
        Optional<DatosAutor> autor = datosLibros.authors().stream().findFirst();
        if (autor.isPresent()) {
            this.author = new Autor(autor.get());
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAuthor() {
        return author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getDownload_count() {
        return download_count;
    }

    @Override
    public String toString() {
        return " \n" +
                "----- Libro -----\n" +
                "Titulo: " + title + "\n" +
                "Autor: " + author.getName() + "\n" +
                "Idioma: " + language + "\n" +
                "Numero de descargas: " + download_count + "\n" +
                "--------------------";
    }


}
