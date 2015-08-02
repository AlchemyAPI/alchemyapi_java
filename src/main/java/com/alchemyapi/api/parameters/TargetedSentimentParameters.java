package com.alchemyapi.api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TargetedSentimentParameters extends Parameters {

    private Boolean showSourceText;
    private String target;

    public boolean isShowSourceText() {
        return showSourceText;
    }

    public void setShowSourceText(boolean showSourceText) {
        this.showSourceText = showSourceText;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrlQuery() {
        String retString = super.getUrlQuery();
        try {
            if (showSourceText != null) retString += "&showSourceText=" + (showSourceText ? "1" : "0");
            if (target != null) retString += "&target=" + URLEncoder.encode(target, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            retString = "";
        }

        return retString;
    }
}
