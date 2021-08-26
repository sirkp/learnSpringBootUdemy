package com.example.learnSpringBoot.exceptions;

import java.util.Date;

import javax.validation.constraints.Null;

import com.example.learnSpringBoot.response.ErrorMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        if (message == null) message = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), message);


        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<?> handleSpecificException(Exception ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        if (message == null) message = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), message);


        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
