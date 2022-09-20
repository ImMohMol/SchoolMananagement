package com.schoolmanagement.schoolmanangemenet.model;

import com.schoolmanagement.schoolmanangemenet.exception.ApiException;

public class Response {
    private ApiException exception;
    private String resultMessage;
    private Object result;
    private Boolean isOk;

    public Response (ApiException exception, String resultMessage, Object result, Boolean isOk) {
        this.exception = exception;
        this.resultMessage = resultMessage;
        this.result = result;
        this.isOk = isOk;
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
        return isOk;
    }

    public void setOk (Boolean ok) {
        isOk = ok;
    }
}
