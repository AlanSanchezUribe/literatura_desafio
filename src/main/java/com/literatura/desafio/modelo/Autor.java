package com.literatura.desafio.modelo;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private Double birth_year;
private Double death_year;
@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Libro> libros;

public Autor() {
}

public Autor(DatosAutor datosAutor) {
    this.name = datosAutor.name();
    this.birth_year = datosAutor.birth_year();
    this.death_year = datosAutor.death_year();
}

public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getName() {    
    return name;
}
public void setName(String name) {
    this.name = name;   
}
public Double getBirth_year() {
    return birth_year;
}
public void setBirth_year(Double birth_year) {
    this.birth_year = birth_year;
}
public Double getDeath_year() {
    return death_year;
}
public void setDeath_year(Double death_year) {
    this.death_year = death_year;
}
public List<Libro> getLibros() {
    return libros;
}
public void setLibros(List<Libro> libros) {
    libros.forEach(libro -> libro.setAuthor(this));
    this.libros = libros;
}

@Override
public String toString() {
    return " \n" +
            "----- Autor -----\n" +
            "Autor: " + name + "\n" +
            "Fecha de nacimiento: " + birth_year + "\n" +
            "Fecha de fallecimiento: " + death_year + "\n" +
            "Libros: " + libros.stream().map(Libro::getTitle).collect(Collectors.toList()) + "\n" +
            "--------------------";
}



}
