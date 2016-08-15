package exceptions.convertexception;

import exceptions.CustomException;

/**
 * Created by vova on 15.08.16.
 */
public class ConvertException extends CustomException {
    public ConvertException() {
        super("Cannot convert the document!");
    }

    public ConvertException(String message) {
        super(message);
    }
}
