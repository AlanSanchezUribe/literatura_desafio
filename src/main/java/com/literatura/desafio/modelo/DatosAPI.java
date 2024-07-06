package com.literatura.desafio.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAPI(
    @JsonAlias("results") List<DatosLibros> resultados) {

}
