package com.cavalcante.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cavalcante.crudspring.model.Course;


// Estrutura que...
@Repository
public interface CourseRepository extends JpaRepository<Long, Course> {

  
}

