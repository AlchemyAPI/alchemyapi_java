package com.alchemyapi.helpers;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kenny
 */
public class ResourceUtils {

    public static String toString(final String resource) {
        try {
            return IOUtils.toString(toInputStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream toInputStream(final String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }

    public static byte[] toBytes(final String resource) {
        try {
            return IOUtils.toByteArray(toInputStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
