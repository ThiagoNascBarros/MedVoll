package org.med.voll.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FilterExceptionErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TreatError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TreatError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream()
                .map(ResponseExceptionError400::new)
                .toList());
    }


    private record ResponseExceptionError400(String field, String message) {
        private ResponseExceptionError400(FieldError error) {
            this
            (
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }

}
