package com.raysi.dtopattern.exception;

// Defines a record to encapsulate error details
public record ErrorMessage(
        // Code representing the type of error (e.g., "404", "500")
        String errorCode,

        // Descriptive message about the error
        String errorMessage
) {
}