package com.study.webservice.resource;

import com.study.webservice.exception.NotFoundException;

import java.io.*;
import java.util.regex.Pattern;

public class ResourceReader {

    private static final String DEFAULT_FILE = "index.html";
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public InputStream readContent(String uri) {
        try {
            File file = getFile(uri);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException("Can't read file", e);
        }
    }

    File getFile(String uri) {
        if (uri == null) {
            uri = DEFAULT_FILE;
        }
        File file = new File(webAppPath, uri);
        if (!file.exists() || !file.isFile()) {
            throw new NotFoundException("File not found");
        }
        return file;
    }

}
