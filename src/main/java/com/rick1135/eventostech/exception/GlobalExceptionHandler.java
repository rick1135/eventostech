package com.rick1135.eventostech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errors=exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .toList();
        ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),"Erro de validação nos dados enviados.",errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
