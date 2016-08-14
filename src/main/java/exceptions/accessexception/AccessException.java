package exceptions.accessexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class AccessException extends CustomException {

    public AccessException() {

    }

    public AccessException(String message) {
        super(message);
    }
}
