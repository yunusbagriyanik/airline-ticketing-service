package com.airlinesticketingbackend.base.exception;


public class ProvideExceptionHandler extends RuntimeException {
    private String message;

    public ProvideExceptionHandler(String message) {
        super(message);
    }

    public ProvideExceptionHandler(String message, String detailedMessage) {
        super(message);
        this.message = detailedMessage;
    }

    public String getDetailedMessage() {
        return message;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.message = detailedMessage;
    }

    @Override
    public String toString() {
        return "ProvideExceptionHandler{" +
                "message='" + message + '\'' +
                '}';
    }
}
