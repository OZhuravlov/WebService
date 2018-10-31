package com.study.webservice.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseHeader {

    private String httpMessage;
    private int httpCode;
    private ZonedDateTime dateTime;
    private String contentType;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

    public ResponseHeader(int httpCode, String httpMessage) {
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
    }

    public void setDateTime() {
        dateTime = ZonedDateTime.now(ZoneOffset.UTC);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HTTP/1.1 ");
        stringBuilder.append(httpCode);
        stringBuilder.append(" ");
        stringBuilder.append(httpMessage);
        stringBuilder.append("\nDate: ");
        stringBuilder.append(dateTime.format(FORMATTER));
        stringBuilder.append("\nServer: WebServer\nLast-Modified: ");
        stringBuilder.append(FORMATTER.format(dateTime));
        if (contentType != null) {
            stringBuilder.append("\nContent-Type: ");
            stringBuilder.append(contentType);
        }
        stringBuilder.append("Connection: Closed\n\n");
        return stringBuilder.toString();
    }
}

