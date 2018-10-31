package com.study.webservice;

import com.study.webservice.server.Server;

public class WebServer {
    public static void main(String[] args) {

        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("resources\\webapp");
        server.start();
    }
}
