package com.api.dtos;

public class ErrorMessageJson {
    private String errorMessage;
    private String stackTrace;

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public ErrorMessageJson() {
    }

    public ErrorMessageJson(String errorMessage, String stackTrace) {
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
