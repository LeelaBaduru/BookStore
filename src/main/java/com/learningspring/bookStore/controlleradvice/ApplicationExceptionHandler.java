package com.learningspring.bookStore.controlleradvice;

import com.learningspring.bookStore.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/* This class gets triggered when the exception is thrown by a controller which is mapped to the ControllerAdvice method using @ExceptionHandler annotation.
    It converts the exceptions to be returned with appropriate error message */


@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(ResourceNotFoundException ex) {

        return ex.getMessage();
    }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
      Map<String, String> errorMap = new HashMap<>();
      ex.getBindingResult().getFieldErrors().forEach(error -> {
          errorMap.put(error.getField(), error.getDefaultMessage());
      });
      return errorMap;
  }


}
