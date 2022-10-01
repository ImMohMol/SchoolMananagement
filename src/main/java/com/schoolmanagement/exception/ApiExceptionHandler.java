package com.schoolmanagement.exception;

import com.schoolmanagement.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler (value = {ApiRequestException.class})
    public ResponseEntity<Response> handleApiException (ApiRequestException apiRequestException) {
        ApiException exception = new ApiException(apiRequestException.getMessage(), HttpStatus.BAD_REQUEST, getTime(ZonedDateTime.now(ZoneId.of("Asia/Tehran"))));
        return ResponseEntity.badRequest().body(new Response(exception, apiRequestException.getMessage(), null, false));
    }

    private String getTime (ZonedDateTime now) {
        return now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
    }
}
