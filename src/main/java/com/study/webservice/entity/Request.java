package com.study.webservice.entity;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String uri;
    private String httpMethod;
    private Map<String, String> headers = new HashMap<String, String>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public String getHeaderByKey(String key){
        return headers.get(key);
    }

}
