package com.study.webservice.service;

import com.study.webservice.entity.ResponseHeader;
import com.study.webservice.entity.StatusCode;

import java.io.*;

public class ResponseWriter {

    private OutputStream socketWriter;
    private ResponseHeader responseHeader = new ResponseHeader();

    public ResponseWriter(OutputStream socketWriter) {
        this.socketWriter = socketWriter;
    }

    public void send(StatusCode statusCode, InputStream inputStream, String contentType) {
        responseHeader.setHttpStatus(statusCode);
        responseHeader.setContentType(contentType);
        try {
            writeHeader();
            byte[] buffer = new byte[1024];
            int count;
            while((count = inputStream.read(buffer)) != -1){
                socketWriter.write(buffer, 0, count);
            }
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't write responseHeader", e);
        }
    }

    public void send(StatusCode statusCode) {
        responseHeader.setHttpStatus(statusCode);
        try {
            writeHeader();
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't write responseHeader", e);
        }
    }

    private void writeHeader() throws IOException {
        responseHeader.setDateTime();
        socketWriter.write(responseHeader.toString().getBytes());
    }

}
