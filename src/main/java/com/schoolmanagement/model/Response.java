package com.schoolmanagement.model;

import com.schoolmanagement.exception.ApiException;

public class Response {
    private ApiException exception;
    private String resultMessage;
    private Object result;
    private Boolean completed;

    public Response (ApiException exception, String resultMessage, Object result, Boolean completed) {
        this.exception = exception;
        this.resultMessage = resultMessage;
        this.result = result;
        this.completed = completed;
    }

    public String getResultMessage () {
        return resultMessage;
    }

    public void setResultMessage (String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResult () {
        return result;
    }

    public void setResult (Object result) {
        this.result = result;
    }

    public Boolean getOk () {
        return completed;
    }

    public void setOk (Boolean ok) {
        completed = ok;
    }

    public ApiException getException () {
        return exception;
    }

    public void setException (ApiException exception) {
        this.exception = exception;
    }
}
