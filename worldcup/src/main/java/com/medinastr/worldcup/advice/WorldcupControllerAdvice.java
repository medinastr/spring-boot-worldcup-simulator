package com.medinastr.worldcup.advice;

import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class WorldcupControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WorldcupInvalidAttributeException.class)
    public final ResponseEntity<String> invalidAttribute(WorldcupInvalidAttributeException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }

    @ExceptionHandler(WorldcupConflictException.class)
    public final ResponseEntity<String> conflict(WorldcupConflictException exc) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exc.getMessage());
    }

    @ExceptionHandler(WorldcupNotFoundException.class)
    public final ResponseEntity<String> notFound(WorldcupNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
    }
}
