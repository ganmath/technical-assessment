/**
 * GlobalExceptionHandler class is a controller advice to handle global exceptions in the application.
 * It specifically handles MethodArgumentTypeMismatchException and provides customized responses.
 */
package com.assessment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentTypeMismatchException and returns a ResponseEntity with a customized error message.
     * If the required type is int, it provides details about the invalid argument type.
     * For other cases, it returns a general "Bad Request" message.
     *
     * @param ex The MethodArgumentTypeMismatchException to be handled.
     * @return ResponseEntity with an appropriate error message and HTTP status.
     */
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
