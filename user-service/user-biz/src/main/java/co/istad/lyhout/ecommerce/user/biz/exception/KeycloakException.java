package co.istad.lyhout.ecommerce.user.biz.exception;

import co.istad.lyhout.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class KeycloakException extends GlobalException {
    public KeycloakException(HttpStatus status, String message) {
        super(status, message);
    }
}

