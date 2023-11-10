package com.sunknowledge.dme.rcm.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private long fieldValue;
    public ResourceNotFoundException(String message) {
        super(message, null, false, false);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
