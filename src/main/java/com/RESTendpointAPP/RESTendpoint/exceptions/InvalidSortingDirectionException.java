package com.RESTendpointAPP.RESTendpoint.exceptions;

import com.RESTendpointAPP.RESTendpoint.entity.TypeOfSorting;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSortingDirectionException extends RuntimeException {


    public InvalidSortingDirectionException(TypeOfSorting typeOfSorting) {
        super("Unknown sorting code: " + typeOfSorting.name());
    }
}
