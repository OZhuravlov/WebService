package com.study.webservice.resource;

import com.study.webservice.exception.NotFoundException;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ResourceReaderTest {

    @Test(expected = NotFoundException.class)
    public void readContentNotFound() {
        String webAppPath = "invalidPath";
        String uri = "invalidFileName";
        ResourceReader resourceReader = new ResourceReader(webAppPath);
        resourceReader.readContent(uri);
    }

    @Test
    public void getDefaultFile() {
        String webAppPath = "resources\\webapp";
        String uri = null;
        ResourceReader resourceReader = new ResourceReader(webAppPath);
        File file = resourceReader.getFile(uri);
        assertEquals("index.html", file.getName());
    }

}
