package com.example.spring_jpa_ecom.exception;

import com.example.spring_jpa_ecom.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Global level exception handler.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex){
        System.out.println(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occured :- " + ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error occured :- " + ex.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
