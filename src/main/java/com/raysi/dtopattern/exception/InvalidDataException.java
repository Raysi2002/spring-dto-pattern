package com.raysi.dtopattern.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotation to generate getter, setter, toString, equals, and hashCode methods
@Data
// Lombok annotation to generate a constructor with all arguments
@AllArgsConstructor
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
// Custom exception class extending RuntimeException
public class InvalidDataException extends RuntimeException {

    // Error code to identify the type of error (e.g., "400", "VALIDATION_ERROR")
    private String errorCode;

    // Detailed message describing the error
    private String errorMessage;
}