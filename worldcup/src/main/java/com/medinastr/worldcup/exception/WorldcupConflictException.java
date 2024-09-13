package com.medinastr.worldcup.exception;

public class WorldcupConflictException extends RuntimeException {

    public final int status = 409;

    public WorldcupConflictException() {
    }

    public WorldcupConflictException(String message) {
        super(message);
    }

    public int getStatus() {
        return status;
    }
}
