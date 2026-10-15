
package com.vasscommerce.vasscommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException ex) {

        List<String> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": "
                        + erro.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(Map.of(
                "erro", "Dados inválidos",
                "detalhes", detalhes
        ));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Map<String, String>> tratarFormatoInvalido(
            Exception ex) {

        return ResponseEntity.badRequest().body(Map.of(
                "erro", "Requisição inválida",
                "detalhes", "Verifique o formato dos dados enviados."
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratarErroInterno(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "erro", "Erro interno",
                        "detalhes", "Não foi possível processar a requisição."
                ));
    }
}
