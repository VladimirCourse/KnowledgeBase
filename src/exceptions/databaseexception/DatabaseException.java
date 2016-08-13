package exceptions.databaseexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class DatabaseException extends CustomException {

    public DatabaseException() {

    }

    public DatabaseException(String message) {
        super(message);
    }
}