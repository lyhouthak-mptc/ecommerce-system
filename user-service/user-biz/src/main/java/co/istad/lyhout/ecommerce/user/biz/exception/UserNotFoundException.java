package co.istad.lyhout.ecommerce.user.biz.exception;

import co.istad.lyhout.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
