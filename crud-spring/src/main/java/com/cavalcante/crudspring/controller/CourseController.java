package com.cavalcante.crudspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcante.crudspring.model.Course;

@RestController  // Fala para o spring que essa classe contem um end-point para acessar a api. Por trás dos panos é um java servlet
@RequestMapping("/api/courses")  // Define o end-point
public class CourseController {
  
  @GetMapping  // @RequestMapping(method = RequestMethod.GET)
  public List<Course> list() {
    
    return null;
  }
}
