package com.literatura.desafio.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    String title, List<DatosAutor> authors, List<String> languages, Double download_count
) {

}
