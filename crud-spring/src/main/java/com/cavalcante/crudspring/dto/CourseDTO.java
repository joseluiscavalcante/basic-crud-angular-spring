package com.cavalcante.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/* 
- Record é uma classe Java, onde o construtor contém todos atributos;
- Não apresenta métodos setters, ou seja, é uma classe imutável;
- O método get é o próprio nome da propriedade.
*/ 
public record CourseDTO(
  @JsonProperty("_id") Long id, 
  @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
  @NotNull @Length(min = 10) @Pattern(regexp = "Back-end|Front-end") String category) {

}