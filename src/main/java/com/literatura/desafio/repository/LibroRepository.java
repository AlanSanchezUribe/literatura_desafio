package com.literatura.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.literatura.desafio.modelo.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {


    Libro findByTitleIgnoreCase(String title);

    List<Libro> findByLanguage(String idioma);
     

   

}
