package com.cavalcante.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cavalcante.crudspring.model.Course;


// Notação usada em conjunto com classes que implementam a lógica de acesso a banco de dados.
@Repository
// Interface que facilita o acesso ao banco de dados com muitos métodos úteis definidos (Spring Data JPA) -- <Entity, PrimaryKeyType>
public interface CourseRepository extends JpaRepository<Course, Long> {
  
  
}

