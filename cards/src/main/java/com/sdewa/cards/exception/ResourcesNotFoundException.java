package com.sdewa.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException(
            String resourceName,
            String filedName,
            String filedValue
    ){
        super(String.format(
                "%s not found with the given input data %s : %s",
                resourceName,
                filedName,
                filedValue
        ));
    }
}
