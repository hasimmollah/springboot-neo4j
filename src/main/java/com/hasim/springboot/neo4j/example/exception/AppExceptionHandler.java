package com.hasim.springboot.neo4j.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ApplicationException ex) {

        ErrorResponse error = new ErrorResponse(
                ErrorCodes.ENTITY_NOT_FOUND_EXCEPTION.getCode(),
                ErrorCodes.ENTITY_NOT_FOUND_EXCEPTION.getDescription());
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                ErrorCodes.APPLICATION_EXCEPTION.getCode(),
                ErrorCodes.APPLICATION_EXCEPTION.getDescription());
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
