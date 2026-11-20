package co.istad.lyhout.ecommerce.user.exception;

import co.istad.lyhout.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserHybridDataInconsistencyException extends GlobalException {
    public UserHybridDataInconsistencyException(HttpStatus status, String message) {
        super(status, message);
    }
}
