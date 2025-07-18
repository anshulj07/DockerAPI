package com.example.DockerAPI.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> GlobalExceptionHandler(CustomException e){
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
