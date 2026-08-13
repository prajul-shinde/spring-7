package com.omsai.spring_7_rest_mvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomErrorHandler {

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
