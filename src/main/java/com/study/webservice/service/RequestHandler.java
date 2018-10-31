package com.study.webservice.service;

import com.study.webservice.entity.Request;
import com.study.webservice.exception.BadRequestException;
import com.study.webservice.exception.InternalServerException;
import com.study.webservice.exception.NotFoundException;
import com.study.webservice.resource.ResourceReader;

import java.io.*;

public class RequestHandler {

    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private BufferedReader reader;
    private OutputStream writer;
    private String webAppPath;

    public RequestHandler(BufferedReader reader, OutputStream outputStream, String webAppPath) {
        this.reader = reader;
        this.writer = outputStream;
        this.webAppPath = webAppPath;
    }

    public void handle() {
        ResponseWriter responseWriter = new ResponseWriter(writer);
        try {
            Request request = RequestParser.parseRequest(reader);
            InputStream resourceReader = new ResourceReader(webAppPath).readContent(request.getUri());
            responseWriter.send(ResponseWriter.SUCCESS_RESPONSE, resourceReader, DEFAULT_CONTENT_TYPE);
        } catch (BadRequestException e){
            responseWriter.send(ResponseWriter.BAD_REQUEST);
        } catch (NotFoundException e) {
            responseWriter.send(ResponseWriter.NOT_FOUND_RESPONSE);
        } catch (InternalServerException e) {
            responseWriter.send(ResponseWriter.INTERNAL_SERVER_ERROR);
        }

    }
}
