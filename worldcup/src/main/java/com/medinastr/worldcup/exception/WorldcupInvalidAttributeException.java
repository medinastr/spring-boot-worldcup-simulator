package com.medinastr.worldcup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorldcupInvalidAttributeException extends RuntimeException {

    public WorldcupInvalidAttributeException() {
    }

    public WorldcupInvalidAttributeException(String message) {
        super(message);
    }
}
