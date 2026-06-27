package com.create.customer.presentation.controller;

import com.create.customer.domain.exception.ErrorResponse;
import com.create.customer.domain.exception.InvalidDataException;
import com.create.customer.domain.exception.InvalidZipCodeException;
import com.create.customer.domain.exception.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import software.amazon.awssdk.core.exception.SdkException;

/**
 * Global exception handler for the API
 */
@Slf4j
@ControllerAdvice
public class HandlerControllerImpl extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        log.warn("Unprocessable entity error: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidZipCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidZipCodeException(InvalidZipCodeException ex) {
        log.warn("Invalid ZIP code error: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        log.warn("Invalid data error: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SdkException.class)
    public ResponseEntity<ErrorResponse> handleSdkException(SdkException ex) {
        log.error("AWS SDK error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponse("AWS error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponse("An unexpected error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}