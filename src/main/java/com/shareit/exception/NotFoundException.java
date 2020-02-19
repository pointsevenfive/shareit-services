package com.shareit.exception;

public class NotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 9063937677624025469L;

    public NotFoundException(String entity) {
        super("Not found: " + entity);
    }

    public  NotFoundException(String obj, String value) {
        super(String.format("Not found: %s value was %s", obj, value));
    }
}
