package com.ezwash.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{
    public ResourceBadRequestException() {
        super();
    }

    public ResourceBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceBadRequestException(String resourceName){
        super (String.format("Resource %s has bad request", resourceName));
    }
}
