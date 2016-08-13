package exceptions.ldapexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class LdapException extends CustomException {

    public LdapException() {

    }

    public LdapException(String message) {
        super(message);
    }
}
