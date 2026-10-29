package com.example.demo.controller;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(MethodArgumentNotValidException exception){
        List<String> exceptions = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        String exMessage = exceptions.get(0);

        ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, exMessage, "api/talks");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraint(jakarta.validation.ConstraintViolationException ex) {
        ApiError error = buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), "/api/talks");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){

        ApiError apiError = buildApiError(HttpStatus.NOT_FOUND, exception.getMessage(), "/api/talks");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResource(DuplicateResourceException exception) {

        ApiError apiError = buildApiError(HttpStatus.CONFLICT, exception.getMessage(),"api/talks");
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> handleBusinessRule(BusinessRuleException exception) {

        ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, exception.getMessage(),"api/talks");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {

        log.error("Gözlənilməyən daxili xəta baş verdi: ", ex);

        ApiError apiError = buildApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Daxili server xətası baş verdi. Zəhmət olmasa bir az sonra yenidən yoxlayın.",
                "api/talks"
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ApiError buildApiError(HttpStatus status, String message, String path) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }


}
