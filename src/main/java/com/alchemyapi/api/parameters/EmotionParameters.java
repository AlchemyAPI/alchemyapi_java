package com.alchemyapi.api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EmotionParameters extends Parameters {
    public static final String CLEANED_OR_RAW = "cleaned_or_raw";
    public static final String CQUERY = "cquery";
    public static final String XPATH = "xpath";

    private String sourceText;
    private Boolean showSourceText;
    private String cQuery;
    private String xPath;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        if (!sourceText.equals(EmotionParameters.CLEANED_OR_RAW)
                && !sourceText.equals(EmotionParameters.CQUERY)
                && !sourceText.equals(EmotionParameters.XPATH)) {
            throw new RuntimeException("Invalid setting " + sourceText + " for parameter sourceText");
        }
        this.sourceText = sourceText;
    }

    public boolean isShowSourceText() {
        return showSourceText;
    }

    public void setShowSourceText(boolean showSourceText) {
        this.showSourceText = showSourceText;
    }

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


    public String getUrlQuery() {
        String retString = super.getUrlQuery();
        try {
            if (sourceText != null) retString += "&sourceText=" + sourceText;
            if (showSourceText != null) retString += "&showSourceText=" + (showSourceText ? "1" : "0");
            if (cQuery != null) retString += "&cquery=" + URLEncoder.encode(cQuery, "UTF-8");
            if (xPath != null) retString += "&xpath=" + URLEncoder.encode(xPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            retString = "";
        }
        return retString;
    }

}
