package com.zipcoder.fakeazon.exception.error;

public class ErrorDetail {
    // A brief title of the error condition, eg: "Validation Failure" or "Internal Server Error"
    private String title;
    // The HTTP status code for the current request; redundant but useful for client-side error handling
    private int status;
    // A short, human-readable description of the error that may be presented to a user
    private String detail;
    // The time in milliseconds when the error occurred
    private long timeStamp;
    // Detailed information such as exception class name or a stack trace useful for developers to debug
    private String developerMessage;

    public ErrorDetail(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
