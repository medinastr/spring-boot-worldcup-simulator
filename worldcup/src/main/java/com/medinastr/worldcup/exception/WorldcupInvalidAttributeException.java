package com.medinastr.worldcup.exception;

public class WorldcupInvalidAttributeException extends RuntimeException {

    public final int status = 400;

    public WorldcupInvalidAttributeException() {
    }

    public WorldcupInvalidAttributeException(String message) {
        super(message);
    }

    public int getStatus() {
        return status;
    }
}
