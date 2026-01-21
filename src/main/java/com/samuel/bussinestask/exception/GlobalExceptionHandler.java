package com.samuel.bussinestask.exception;

import com.samuel.bussinestask.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarNoEncontrado(
            UserNoEncontradoException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(
                        "USUARIO_NO_ENCONTRADO",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler({
            EmailDuplicadoException.class,
            NombreUsuarioDuplicadoException.class
    })
    public ResponseEntity<ErrorResponseDTO> manejarDuplicados(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(
                        "DATO_DUPLICADO",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }
}