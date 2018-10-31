package com.study.webservice.service;

import com.study.webservice.entity.ResponseHeader;

import java.io.*;

public class ResponseWriter {

    public final static ResponseHeader SUCCESS_RESPONSE = new ResponseHeader(200, "OK");
    public final static ResponseHeader NOT_FOUND_RESPONSE = new ResponseHeader(404, "Not Found");
    public final static ResponseHeader BAD_REQUEST = new ResponseHeader(400, "Bad Request");
    public final static ResponseHeader INTERNAL_SERVER_ERROR = new ResponseHeader(500, "Internal Server Error");

    private OutputStream socketWriter;

    public ResponseWriter(OutputStream socketWriter) {
        this.socketWriter = socketWriter;
    }

    public void send(ResponseHeader response, InputStream inputStream, String contentType) {
        response.setContentType(contentType);
        try {
            writeHeader(response);
            byte[] buffer = new byte[1024];
            int count;
            while((count = inputStream.read(buffer)) != -1){
                socketWriter.write(buffer, 0, count);
            }
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't write response", e);
        }
    }

    public void send(ResponseHeader response) {
        try {
            writeHeader(response);
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't write response", e);
        }
    }

    private void writeHeader(ResponseHeader response) throws IOException {
        response.setDateTime();
        socketWriter.write(response.toString().getBytes());
    }

}
