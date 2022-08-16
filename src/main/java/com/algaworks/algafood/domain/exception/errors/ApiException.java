package com.algaworks.algafood.domain.exception.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private String error;
    private HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.error = message;
        this.status = status;
    }
}
