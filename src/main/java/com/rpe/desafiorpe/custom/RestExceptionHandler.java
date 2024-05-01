package com.rpe.desafiorpe.custom;

import com.rpe.desafiorpe.exceptions.InvalidCpfException;
import com.rpe.desafiorpe.exceptions.InvalidPhoneNumberException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCpfException.class)
    private ResponseEntity<ErrorMessage> invalidCpf(InvalidCpfException exception){
        ErrorMessage res = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    private ResponseEntity<ErrorMessage> invalidPhoneNumber(InvalidPhoneNumberException exception){
        ErrorMessage res = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorMessage> notFound(RuntimeException exception){
        ErrorMessage res = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

}
