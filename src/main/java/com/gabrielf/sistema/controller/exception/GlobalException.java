package com.gabrielf.sistema.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.gabrielf.sistema.controller.exception.Exception.NoSuchElementExceptionCandidato;
import com.gabrielf.sistema.controller.exception.Exception.NoSuchElementExceptionVaga;

/* A GlobalException busca exceptions não tratadas na API e lança esses erros*/ 

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {


  private final Logger logger = LoggerFactory.getLogger(GlobalException.class);


  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> dataIntegrityHandler(DataIntegrityViolationException data){
    return new ResponseEntity<>(data.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(NoSuchElementExceptionCandidato.class)
  public ResponseEntity<String> noSuchElementCandidatoHandler(NoSuchElementExceptionCandidato exceptionCandidato){
    return new ResponseEntity<>(exceptionCandidato.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<String> errorUnexpectedHandler(Throwable erroInesperado){
    var msg = "Ocorreu um Erro Inesperado";
    logger.error(msg, erroInesperado);
    return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NoSuchElementExceptionVaga.class)
  public ResponseEntity<String> noSuchElementVagaHandler(NoSuchElementExceptionVaga exceptionVaga){
    return new ResponseEntity<>(exceptionVaga.getMessage(), HttpStatus.NOT_FOUND);
  }


}
