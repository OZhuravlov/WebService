package com.study.webservice.entity;

public enum StatusCode {
    SUCCESS("200", "OK"),
    NOT_FOUND("404", "Not Found"),
    BAD_REQUEST("400", "Bad Request"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed");

    private String statusCode;
    private String statusMessage;

    StatusCode(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String generateResponseLine() {
        return "HTTP/1.1 " + statusCode + " " + statusMessage;
    }

}
