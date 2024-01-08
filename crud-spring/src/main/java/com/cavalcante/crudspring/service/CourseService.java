package com.cavalcante.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cavalcante.crudspring.dto.CourseDTO;
import com.cavalcante.crudspring.dto.mapper.CourseMapper;
import com.cavalcante.crudspring.exception.RecordNotFoundException;
import com.cavalcante.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
  private final CourseRepository courseRepository;  // Considerado como final para garantir que não haverá nenhuma alteração dessa instância.
  private final CourseMapper courseMapper;

  public CourseService (CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

  public List<CourseDTO> list() {
    return courseRepository.findAll()
      .stream()
      .map(courseMapper::toDTO)
      .collect(Collectors.toList());
  }

  public CourseDTO findById(@NotNull @Positive Long id) {
    // Se o objeto não for encontrado no BD, lançamos uma exceção.
    return courseRepository.findById(id)
      .map(courseMapper::toDTO)
      .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CourseDTO create (@Valid @NotNull CourseDTO courseDTO) {
    return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
  }

  public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
    return courseRepository.findById(id)
    // Se um valor estiver presente, aplique a função de mapeamento fornecido a ele.
    .map(recordFound -> {
      // Alteração dos valores.
      recordFound.setName(courseDTO.name());
      recordFound.setCategory(courseDTO.category());
      // Transforma em DTO e retorna o objeto com os valores atualizados.
      return courseMapper.toDTO(courseRepository.save(recordFound));
    })
    .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive Long id) {
    courseRepository.delete(courseRepository.findById(id)
    .orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
