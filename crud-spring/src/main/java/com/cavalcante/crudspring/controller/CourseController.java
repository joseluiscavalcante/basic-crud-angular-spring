package com.cavalcante.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcante.crudspring.dto.CourseDTO;
import com.cavalcante.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
// import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@Validated  // Necessário devido ao uso de validações nos parâmetros dos métodos - como @NotNull e @Positive
@RestController  // Fala para o spring que essa classe contem um end-point para acessar a api. Por trás dos panos é um java servlet
@RequestMapping("/api/courses")  // Define o end-point
// @AllArgsConstructor  // Cria construtor automaticamente.
public class CourseController {

  private final CourseService courseService;
  
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping  // ~~@RequestMapping(method = RequestMethod.GET)
  public List<CourseDTO> list() {
    return courseService.list();
  }

  // Complementa a URL da API, permitindo que seja especificado um ID para retornar apenas 1 objeto
  @GetMapping("/{id}")
  // PathVariable: Indica que estamos capturando o valor do id passado no caminho da API
  public CourseDTO findById(@PathVariable("id") @NotNull @Positive Long id) {
    return courseService.findById(id);
  }

  @PostMapping  // ~~@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(code = HttpStatus.CREATED)  // Altera o status http da resposta para 201 - "A solicitação foi atendida, resultando na criação de um novo recurso".
  // @RequestBody --> indica que um controller method deve esperar o corpo da requisição HTTP para ser convertido automaticamente em um objeto Java correspondente.
  public CourseDTO create (@RequestBody @Valid CourseDTO courseDTO) {
    return courseService.create(courseDTO);
  }

  @PutMapping("/{id}")
  public CourseDTO update(@PathVariable("id") @NotNull @Positive Long id, @RequestBody @Valid CourseDTO courseDTO) {
    return courseService.update(id, courseDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)  // Retorna um 204 No Content - O servidor processou a solicitação com êxito e não está retornando nenhum conteúdo.
  public void delete(@PathVariable("id") @NotNull @Positive Long id) {
    courseService.delete(id);
  }
}
