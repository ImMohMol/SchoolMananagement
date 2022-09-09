package com.schoolmanagement.schoolmanangemenet.model;

public class Response {
    private String resultMessage;
    private Object result;
    private Boolean isOk;

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
