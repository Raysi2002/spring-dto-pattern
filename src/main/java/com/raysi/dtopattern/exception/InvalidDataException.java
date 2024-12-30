package com.raysi.dtopattern.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidDataException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
}
