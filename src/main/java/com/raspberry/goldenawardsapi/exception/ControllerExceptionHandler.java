package com.raspberry.goldenawardsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> noContent(final NoContentException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
