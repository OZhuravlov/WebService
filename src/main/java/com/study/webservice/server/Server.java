package com.study.webservice.server;

import com.study.webservice.service.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private String webAppPath;
    private ServerSocket serverSocket;

    public void start() {
        serverSocket = connect();
        while (true) {
            processClientRequest(serverSocket);
        }
    }

    private ServerSocket connect() {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't listen to port " + port, e);
        }
    }

    private void processClientRequest(ServerSocket serverSocket) {
        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            RequestHandler requestHandler = new RequestHandler(reader, outputStream, webAppPath);
            requestHandler.handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

}
