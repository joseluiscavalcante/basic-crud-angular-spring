package com.cavalcante.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data  // Fornece os getters e setters ++
@Entity  // Especificando a classe como uma entidade, que faz mapeamento com o Banco de dados
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")  // Modifica o código sql utilizado pelo deleteById()
@SQLRestriction(value = "status = 'Ativo'")  // Adiciona esse filtro na cláusula WHERE dos SELECT's
// @Table(name = "cursos") --> Se a tabela já existir
public class Course {

  @Id  // Chave primária
  @GeneratedValue(strategy = GenerationType.AUTO)  // Chave sequencial - Gerar automaticamente
  @JsonProperty("_id")  // Permite escolher novo nome para a propriedade quando ela virar JSON
  // @JsonIgnore --> Ignora a propriedade e não envia para o JSON
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 3, max = 100)
  //@Column(name = "nome") --> Associar ao nome real na tabela do banco
  @Column(length = 100, nullable = false)  // Especifica o tamanho da string e informa que ela não pode ser nula
  private String name;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Back-end|Front-end")
  @Column(length = 10, nullable = false)
  private String category;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Ativo|Inativo")
  @Column(length = 10, nullable = false)
  private String status = "Ativo";
}
