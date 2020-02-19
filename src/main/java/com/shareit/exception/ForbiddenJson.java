package com.shareit.exception;

public class ForbiddenJson {

    public String status = "403";

    public String message = "Forbidden";

    public String exception;

    public ForbiddenJson(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
