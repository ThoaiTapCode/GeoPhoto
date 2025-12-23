package com.geophoto.controller;

import com.geophoto.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler
 * Handles validation errors and other exceptions
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * Handle validation errors from @Valid annotation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        // Get first error message
        String firstError = errors.values().iterator().next();
        log.warn("Validation error: {}", firstError);
        
        return ResponseEntity.badRequest()
                .body(new MessageResponse(firstError));
    }
    
    /**
     * Handle generic runtime exceptions
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException ex) {
        log.error("Runtime exception: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(new MessageResponse(ex.getMessage()));
    }
    
    /**
     * Handle all other exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGenericException(Exception ex) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Lỗi không xác định: " + ex.getMessage()));
    }
}

