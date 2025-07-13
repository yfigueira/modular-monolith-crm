package org.example.usermodule.exceptionhandler;

import org.example.usermodule.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserServiceException(UserException.ResourceNotFoundException ex) {
        var exceptionResponse = ExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }
}
