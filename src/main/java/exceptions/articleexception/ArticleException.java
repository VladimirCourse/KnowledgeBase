package exceptions.articleexception;

import exceptions.CustomException;

/**
 * Created by vova on 13.08.16.
 */
public class ArticleException extends CustomException {

    public ArticleException() {

    }

    public ArticleException(String message) {
        super(message);
    }
}