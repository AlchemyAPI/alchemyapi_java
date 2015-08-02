package com.alchemyapi.api.parameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ConstraintQueryParameters extends Parameters {

    private String cQuery;

    public String getCQuery() {
        return cQuery;
    }

    public void setCQuery(String cQuery) {
        this.cQuery = cQuery;
    }


    public String getUrlQuery() {
        String retString = super.getUrlQuery();
        try {
            if (cQuery != null) retString += "&cquery=" + URLEncoder.encode(cQuery, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            retString = "";
        }
        return retString;
    }


}



