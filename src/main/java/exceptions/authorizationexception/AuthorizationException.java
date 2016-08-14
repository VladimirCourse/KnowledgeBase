package exceptions.authorizationexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class AuthorizationException extends CustomException {

    public AuthorizationException() {

    }

    public AuthorizationException(String message) {
        super(message);
    }
}