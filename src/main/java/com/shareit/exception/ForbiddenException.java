package com.shareit.exception;

public class ForbiddenException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6595262224047298317L;

    public ForbiddenException(String exception) {
        super("Resource: " + exception);

    }
}
