package com.cavalcante.crudspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcante.crudspring.model.Course;
import com.cavalcante.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController  // Fala para o spring que essa classe contem um end-point para acessar a api. Por trás dos panos é um java servlet
@RequestMapping("/api/courses")  // Define o end-point
@AllArgsConstructor  // Cria construtor automaticamente.
public class CourseController {

  private final CourseRepository courseRepository;  // Considerado como final para garantir que não haverá nenhuma alteração dessa instância.
  
  @GetMapping  // ~~@RequestMapping(method = RequestMethod.GET)
  public List<Course> list() {
    return courseRepository.findAll();  // Realiza um "SELECT *" na tabela
  }

  // Complementa a URL da API, permitindo que seja especificado um ID para retornar apenas 1 objeto
  @GetMapping("/{id}")
  // PathVariable: Indica que estamos capturando o valor do id passado no caminho da API
  public Optional<Course> findById(@PathVariable("id") Long id) {
    return courseRepository.findById(id);
    
  }

  @PostMapping  // ~~@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(code = HttpStatus.CREATED)  // Altera o status http da resposta para 201 - "A solicitação foi atendida, resultando na criação de um novo recurso".
  // @RequestBody --> indica que um controller method deve esperar o body da requisição HTTP para ser convertido automaticamente em um objeto Java correspondente.
  public Course create (@RequestBody Course course) {
    // Salva e retorna o valor recebido
    return courseRepository.save(course);
  }
}
