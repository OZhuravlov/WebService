package com.study.webservice.service;

import com.study.webservice.entity.HttpRequestType;
import com.study.webservice.entity.Request;
import com.study.webservice.exception.BadRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private static final Pattern pattern = Pattern.compile("([^\\s]+)\\s+([^\\s]+)\\s*([^\\s]*)");

    public static Request parseRequest(BufferedReader reader) {
        Request request = new Request();
        try {
            injectUriAndMethod(request, reader.readLine());
            injectHeaders(request, reader);
            return request;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("Bad HTTP request header", e);
        }
    }

    private static void injectUriAndMethod(Request request, String requestLine) {
        Matcher matcher = pattern.matcher(requestLine);
        if(matcher.matches()){
            request.setHttpMethod(matcher.group(1));
            String path = matcher.group(2);
            if(!path.isEmpty() && !path.equals("/")){
                request.setUri(path);
            }
        }
    }

    private static void injectHeaders(Request request, BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] header = line.split(": ");
            request.addHeader(header[0], header[1]);
        }
    }
}