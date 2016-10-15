package com.alchemyapi.api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ImageParameters extends Parameters {
    public static final String CQUERY = "cquery";
    public static final String XPATH = "xpath";
    public static final String RAW = "raw";
    public static final String NOT_RAW = "not-raw";
    private String cQuery;
    private String xPath;
    private Integer maxRetrieve;
    private byte[] image;
    private String imagePostMode;
    private String baseUrl;

    public String getCQuery() {
        return cQuery;
    }

    public void setCQuery(String cQuery) {
        this.cQuery = cQuery;
    }

    public String getXPath() {
        return xPath;
    }

    public void setXPath(String xPath) {
        this.xPath = xPath;
    }

    public int getMaxRetrieve() {
        return maxRetrieve;
    }

    public void setMaxRetrieve(int maxRetrieve) {
        this.maxRetrieve = maxRetrieve;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImagePostMode() {
        return imagePostMode;
    }

    public void setImagePostMode(String imagePostMode) {
        this.imagePostMode = imagePostMode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrlQuery() {
        String retString = super.getUrlQuery();
        try {
            if (cQuery != null) retString += "&cquery=" + URLEncoder.encode(cQuery, "UTF-8");
            if (xPath != null) retString += "&xpath=" + URLEncoder.encode(xPath, "UTF-8");
            if (maxRetrieve != null) retString += "&maxRetrieve=" + maxRetrieve.toString();
            if (imagePostMode != null) retString += "&imagePostMode=" + URLEncoder.encode(imagePostMode, "UTF-8");
            if (baseUrl != null) retString += "&baseUrl=" + URLEncoder.encode(baseUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            retString = "";
        }
        return retString;
    }
}
