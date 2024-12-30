package com.raysi.dtopattern.exception;

public record ErrorMessage(
        String errorCode,
        String errorMessage
) {
}
