package exceptions.authorizationexception;

/**
 * Created by vova on 13.08.16.
 */
public class WrongUsernameException extends AuthorizationException {

    public WrongUsernameException() {
        super("Wrong username!");
    }

    public WrongUsernameException(String message) {
        super(message);
    }
}