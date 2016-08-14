package exceptions.authorizationexception;

/**
 * Created by vova on 13.08.16.
 */
public class WrongPasswordException extends AuthorizationException {

    public WrongPasswordException() {
        super("Wrong password!");
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
