package com.cavalcante.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.bind.annotation.PutMapping;


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
  public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
    // Optional: O objeto pode existir ou não no banco de dados, se não, devemos fazer o tratamento do erro.
    return courseRepository.findById(id)
      .map(data -> ResponseEntity.ok().body(data))  // Se o objeto existir [ok()] retorne os dados no body;
      .orElse(ResponseEntity.notFound().build());  // Se o objeto não for encontrado, envie uma mensagem de erro.
  }

  @PostMapping  // ~~@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(code = HttpStatus.CREATED)  // Altera o status http da resposta para 201 - "A solicitação foi atendida, resultando na criação de um novo recurso".
  // @RequestBody --> indica que um controller method deve esperar o body da requisição HTTP para ser convertido automaticamente em um objeto Java correspondente.
  public Course create (@RequestBody Course course) {
    // Salva e retorna o valor recebido
    return courseRepository.save(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable("id") Long id, @RequestBody Course course) {
    return courseRepository.findById(id)
    // Se um valor estiver presente, aplique a função de mapeamento fornecido a ele.
    .map(recordFound -> {
      // Alteração dos valores.
      recordFound.setName(course.getName());
      recordFound.setCategory(course.getCategory());
      // Salvando o objeto com os valores atualizados.
      Course updated = courseRepository.save(recordFound);
      // Retorno do PUT para informar o resultado da operação, indicando que a atualização foi concluída com sucesso.
      return ResponseEntity.ok().body(updated);
    })
    .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    return courseRepository.findById(id)
    .map(recordFound -> {
      courseRepository.deleteById(id);
      // Retorna um 204 No Content - O servidor processou a solicitação com êxito e não está retornando nenhum conteúdo.
      return ResponseEntity.noContent().<Void>build();
    })
    .orElse(ResponseEntity.notFound().build());
  }
}
