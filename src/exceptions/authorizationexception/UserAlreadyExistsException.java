package exceptions.authorizationexception;

/**
 * Created by vova on 13.08.16.
 */
public class UserAlreadyExistsException extends AuthorizationException{

    public UserAlreadyExistsException() {
        super("Username already taken!");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}