package com.cavalcante.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cavalcante.crudspring.exception.RecordNotFoundException;
import com.cavalcante.crudspring.model.Course;
import com.cavalcante.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
  private final CourseRepository courseRepository;  // Considerado como final para garantir que não haverá nenhuma alteração dessa instância.

  public CourseService (CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> list() {
    return courseRepository.findAll();  // Realiza um "SELECT *" na tabela
  }

  public Course findById(@NotNull @Positive Long id) {
    // Se o objeto não for encontrado no BD, lançamos uma exceção.
    return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Course create (@Valid Course course) {
    return courseRepository.save(course);
  }

  public Course update(@NotNull @Positive Long id, @Valid Course course) {
    return courseRepository.findById(id)
    // Se um valor estiver presente, aplique a função de mapeamento fornecido a ele.
    .map(recordFound -> {
      // Alteração dos valores.
      recordFound.setName(course.getName());
      recordFound.setCategory(course.getCategory());
      // Retornando o objeto com os valores atualizados.
      return courseRepository.save(recordFound);
    })
    .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive Long id) {
    courseRepository.delete(courseRepository.findById(id)
    .orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
