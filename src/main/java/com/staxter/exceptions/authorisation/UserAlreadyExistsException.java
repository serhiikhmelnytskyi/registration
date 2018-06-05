package com.staxter.exceptions.authorisation;

/**
 * Created by serge on 6/4/2018.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
