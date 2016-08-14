package exceptions.accessexception;

/**
 * Created by vova on 13.08.16.
 */
public class NoAccessException extends  AccessException{

    public NoAccessException() {
        super("No access rights!");
    }

    public NoAccessException(String message) {
        super(message);
    }
}