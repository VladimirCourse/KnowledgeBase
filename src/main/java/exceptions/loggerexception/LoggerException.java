package exceptions.loggerexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class LoggerException extends CustomException {

    public LoggerException() {

    }

    public LoggerException(String message) {
        super(message);
    }
}