package com.schoolmanagement.exception;

import org.springframework.http.HttpStatus;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String time;

    public ApiException (String message,
                         HttpStatus httpStatus,
                         String time) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public String getMessage () {
        return message;
    }

    public HttpStatus getHttpStatus () {
        return httpStatus;
    }

    public String getTimeStamp () {
        return time;
    }
}
