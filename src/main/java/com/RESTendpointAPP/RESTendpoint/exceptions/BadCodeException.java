package com.RESTendpointAPP.RESTendpoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadCodeException extends RuntimeException{

    public BadCodeException(String message) {
        super(message);
    }
}
