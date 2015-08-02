package com.alchemyapi.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kenny
 */
public class ResourceUtils {

    public static String toString(final String resource) throws IOException {
        return IOUtils.toString(toInputStream(resource));
    }

    public static InputStream toInputStream(final String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }

}
