package com.shareit.exception;

public class NotAuthorizedException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 8133989583219485023L;

    public NotAuthorizedException(String username) {
        super(String.format("Unauthorised attempt with id: %s", username));
    }
}
