package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.dto.response.ErrorResponse;
import com.algaworks.algafood.domain.exception.errors.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> HandleObjectNotFound(ApiException err, HttpServletRequest request) {
        ErrorResponse errorObjetc = ErrorResponse.generateErrorResponse(err.getError(), err.getStatus().value(), request);

        log.error("Message error: " + err.getMessage());
        return ResponseEntity.status(errorObjetc.getStatus()).body(errorObjetc);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> HandleGenericException(RuntimeException err, HttpServletRequest request) {

        ErrorResponse errorObjetc = ErrorResponse.generateErrorResponse(err.getMessage(), HttpStatus.BAD_REQUEST.value(), request);
        log.error("Message error: " + err.getMessage());
        return ResponseEntity.status(errorObjetc.getStatus()).body(errorObjetc);
    }
}
