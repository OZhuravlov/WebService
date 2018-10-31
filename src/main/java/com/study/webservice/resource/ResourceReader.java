package com.study.webservice.resource;

import com.study.webservice.exception.NotFoundException;

import java.io.*;
import java.util.regex.Pattern;

public class ResourceReader {

    private static final Pattern PATTERN = Pattern.compile("([^\\?]+)\\??(.*)");
    private static final String DEFAULT_FILE = "index.html";
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public InputStream readContent(String uri) {
        if (uri == null){
            uri = DEFAULT_FILE;
        }
        try {
            File file = new File(webAppPath, uri);
            if (!file.exists() || !file.isFile()) {
                throw new NotFoundException("File not found");
            }
            return new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException("File " + uri + " is not found", e);
        }
    }

}
