package co.istad.lyhout.ecommerce.customer.restapi.exception;

import co.istad.lyhout.ecommerce.customer.domain.exception.CustomerDomainException;
import co.istad.lyhout.ecommerce.restapi.dto.RestApiErrorResponse;
import co.istad.lyhout.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerDomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestApiErrorResponse<?> handleCustomerDomainException(CustomerDomainException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
}
