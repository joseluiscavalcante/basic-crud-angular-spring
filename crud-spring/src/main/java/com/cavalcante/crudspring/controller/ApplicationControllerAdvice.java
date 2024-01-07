package com.cavalcante.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cavalcante.crudspring.exception.RecordNotFoundException;

@RestControllerAdvice  // Classe que vai informar a todos os RestControllers o que fazer com as exceções.
public class ApplicationControllerAdvice {
  // Manipulador de Exceções
  @ExceptionHandler(RecordNotFoundException.class)  // Informa de qual exceção está sendo manipulada.
  @ResponseStatus(HttpStatus.NOT_FOUND)  // Exibe o erro 404 quando a exceção for lançada.
  public String handleNotFoundException(RecordNotFoundException ex) {
    return ex.getMessage();
  }
}
