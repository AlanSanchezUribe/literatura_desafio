package com.literatura.desafio.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record DatosAutor(String name,  Double birth_year, Double death_year) {

}
