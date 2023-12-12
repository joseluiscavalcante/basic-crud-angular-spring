package com.cavalcante.crudspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data  // Fornece os getters e setters ++
@Entity  // Especificando a classe como uma entidade, que faz mapeamento com o Banco de dados
// @Table(name = "cursos") --> Se a tabela já existir
public class Course {

  @Id  // Chave primária
  @GeneratedValue(strategy = GenerationType.AUTO)  // Chave sequencial - Gerar automaticamente
  @JsonProperty("_id")  // Permite escolher novo nome para a propriedade quando ela virar JSON
  // @JsonIgnore --> Ignora a propriedade e não envia para o JSON
  private Long id;

  //@Column(name = "nome") --> Associar ao nome real na tabela do banco
  @Column(length = 200, nullable = false)  // Especifica o tamanho da string e informa que ela não pode ser nula
  private String name;

  @Column(length = 200, nullable = false)
  private String category;


}
