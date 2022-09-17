package com.algaworks.algafood.infrastructure.exception.dto.response;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
    private long timestamp;
    private int status;
    private String error;
    private String path;

    public static ErrorResponse generateErrorResponse(final String error, final int status, final HttpServletRequest request) {
        return ErrorResponse.builder()
                .error(error)
                .path(request.getRequestURI())
                .status(status)
                .timestamp(System.currentTimeMillis()).build();
    }
}
