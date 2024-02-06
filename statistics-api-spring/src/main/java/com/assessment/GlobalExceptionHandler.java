package com.assessment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == int.class) {
            // Handle the case where an invalid integer is provided
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format("Invalid argument type for parameter '%s': %s", ex.getName(), ex.getValue()));
        }

        // For other cases, provide a general error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
    }

    // Additional exception handlers can be added here
}
