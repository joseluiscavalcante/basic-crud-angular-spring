package com.cavalcante.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.cavalcante.crudspring.dto.CourseDTO;
import com.cavalcante.crudspring.model.Course;

@Component
public class CourseMapper {
  
  public CourseDTO toDTO(Course course) {
    if(course == null) {
      return null;
    }
    return new CourseDTO(course.getId(), course.getName(), course.getCategory());
  }

  public Course toEntity(CourseDTO courseDTO) {
    // Pesquisar padrão builder depois**
    if(courseDTO == null) {
      return null;
    }
    Course course = new Course();
    if(courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(courseDTO.category());
    course.setStatus("Ativo");

    return course;
  }
}
