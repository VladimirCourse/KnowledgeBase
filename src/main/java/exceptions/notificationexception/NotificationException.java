package exceptions.notificationexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class NotificationException extends CustomException {

    public NotificationException() {

    }

    public NotificationException(String message) {
        super(message);
    }
}