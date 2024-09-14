package com.medinastr.worldcup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorldcupNotFoundException extends RuntimeException{

    public WorldcupNotFoundException() {
    }

    public WorldcupNotFoundException(String message) {
        super(message);
    }
}
