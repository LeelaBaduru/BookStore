package com.learningspring.bookStore.controlleradvice;

import com.learningspring.bookStore.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/* This class gets triggered when the exception is thrown by a controller which is mapped to the ControllerAdvice method using @ExceptionHandler annotation.
    It converts the exceptions to be returned with appropriate error message */

@ControllerAdvice
public class ResourceNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

}
