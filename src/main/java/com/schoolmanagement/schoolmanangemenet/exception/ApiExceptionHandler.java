package com.schoolmanagement.schoolmanangemenet.exception;

import com.schoolmanagement.schoolmanangemenet.model.Response;
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
        ApiException exception = new ApiException(apiRequestException.getMessage(), HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.badRequest().body(new Response(exception, apiRequestException.getMessage(), null, false));
    }
}
