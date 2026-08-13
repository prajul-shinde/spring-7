package com.omsai.spring_7_rest_mvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler
    ResponseEntity handleJpaViolations(TransactionSystemException e) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();
        if (e.getCause().getCause() instanceof ConstraintViolationException ce) {
            List errors = ce.getConstraintViolations().stream()
                    .map(c -> {
                        Map<String, String> map = new HashMap<>();
                        map.put(c.getPropertyPath().toString(), c.getMessage());
                        return map;
                    }).toList();
            return responseEntity.body(errors);
        }
        return responseEntity.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException e) {
        List errorList = e.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errormap = new HashMap<>();
                    errormap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errormap;
                }).toList();
        return ResponseEntity.badRequest().body(errorList);
    }
}
