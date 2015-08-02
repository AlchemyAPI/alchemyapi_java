package com.alchemyapi.api.parameters;


public class TextParameters extends Parameters {
    private Boolean useMetaData;
    private Boolean extractLinks;

    public boolean isUseMetaData() {
        return useMetaData;
    }

    public void setUseMetaData(boolean useMetaData) {
        this.useMetaData = useMetaData;
    }

    public boolean isExtractLinks() {
        return extractLinks;
    }

    public void setExtractLinks(boolean extractLinks) {
        this.extractLinks = extractLinks;
    }

    public String getUrlQuery() {
        String retString = super.getUrlQuery();

        if (useMetaData != null) retString += "&useMetaData=" + (useMetaData ? "1" : "0");
        if (extractLinks != null) retString += "&extractLinks=" + (extractLinks ? "1" : "0");

        return retString;
    }
}
