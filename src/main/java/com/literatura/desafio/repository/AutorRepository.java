package com.literatura.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.literatura.desafio.modelo.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByName(String name);

    @Query ("select a from Autor a where a.birth_year <= :year and a.death_year > :year")
    List<Autor> autoresVivos(int year);
}
