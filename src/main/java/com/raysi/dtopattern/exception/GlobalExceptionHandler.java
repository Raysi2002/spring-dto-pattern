package com.raysi.dtopattern.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// A centralized exception handling mechanism for the entire application
@RestControllerAdvice
// Extends RuntimeException, but extending is unnecessary here unless custom exception functionality is added
public class GlobalExceptionHandler extends RuntimeException {

    // Handles exceptions of type ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        // Constructs an ErrorMessage object using details from the exception
        ErrorMessage errorMessage = new ErrorMessage(
                resourceNotFoundException.getErrorCode(),
                resourceNotFoundException.getErrorMessage()
        );
        // Returns the error response with NOT_FOUND (404) status
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Handles exceptions of type InvalidDataException (this method should be annotated with @ExceptionHandler)
    // This is likely a missing annotation and should be fixed.
    public ResponseEntity<ErrorMessage> invalidDataException(InvalidDataException invalidDataException) {
        // Constructs an ErrorMessage object using details from the exception
        ErrorMessage errorMessage = new ErrorMessage(
                invalidDataException.getErrorCode(),
                invalidDataException.getErrorMessage()
        );
        // Returns the error response with BAD_REQUEST (400) status and additional header
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("Accept-Datetime") // Adds a custom header to the response
                .body(errorMessage);
    }
}