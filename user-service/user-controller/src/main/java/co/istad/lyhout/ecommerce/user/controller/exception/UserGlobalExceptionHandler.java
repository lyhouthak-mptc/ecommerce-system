package co.istad.lyhout.ecommerce.user.controller.exception;

import co.istad.lyhout.ecommerce.restapi.dto.RestApiErrorResponse;
import co.istad.lyhout.ecommerce.restapi.exception.GlobalException;
import co.istad.lyhout.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Handles validation errors (from parent) and user-service exceptions
@RestControllerAdvice
public class UserGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<RestApiErrorResponse<?>> handleGlobalException(GlobalException e) {
        return ResponseEntity.status(e.getStatus())
                .body(RestApiErrorResponse.builder()
                        .code(e.getStatus().getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }
}
