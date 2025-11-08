package danieltsuzuki.com.github.apirestfulparte2.controller;

import danieltsuzuki.com.github.apirestfulparte2.service.exception.NotFoundException;
import danieltsuzuki.com.github.apirestfulparte2.service.exception.SessionExpiredException;
import danieltsuzuki.com.github.apirestfulparte2.service.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> UnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> NotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<String> SessionExpiredException(SessionExpiredException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
