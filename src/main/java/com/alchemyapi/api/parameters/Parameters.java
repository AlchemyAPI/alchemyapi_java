package com.alchemyapi.api.parameters;

import com.alchemyapi.api.exceptions.AlchemyApiException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;


public class Parameters {
    public static final String OUTPUT_JSON = "json";
    public static final String OUTPUT_XML = "xml";
    public static final String OUTPUT_RDF = "rdf";

    private String url;
    private String html;
    private String text;
    private String outputMode = OUTPUT_XML; // TODO make json default
    private String customParameters;
    private String encoding = "UTF-8";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        validateUrl(url);
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        validateHtml(html);
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        validateText(text);
        this.text = text;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

    public String getOutputMode() {
        return outputMode;
    }

    public void setOutputMode(String outputMode) {
        if (!outputMode.equals(Parameters.OUTPUT_XML) && !outputMode.equals(OUTPUT_RDF)) {
            throw new RuntimeException("Invalid setting " + outputMode + " for parameter outputMode");
        }
        this.outputMode = outputMode;
    }

    public String getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(Map<String, String> customParameters) {
        final StringBuilder data = new StringBuilder();
        try {
            for(Map.Entry<String, String> parameter : customParameters.entrySet()) {
                data.append('&')
                    .append(parameter.getKey())
                    .append('=')
                    .append(URLEncoder.encode(parameter.getValue(), "UTF-8"));
            }

            this.customParameters = data.toString();
        } catch (UnsupportedEncodingException e) {
            throw new AlchemyApiException(e);
        }
    }

    public String getUrlQuery() {
        final StringBuilder urlQuery = new StringBuilder();
        try {
            if (url != null) { urlQuery.append("&url=").append(URLEncoder.encode(url, "UTF-8")); }
            if (html != null) { urlQuery.append( "&html=").append(URLEncoder.encode(html, "UTF-8")); }
            if (text != null) { urlQuery.append("&text=").append(URLEncoder.encode(text, "UTF-8")); }
            if (customParameters != null) { urlQuery.append(customParameters); }
            if (outputMode != null) { urlQuery.append("&outputMode=").append(outputMode); }
            return urlQuery.toString();

        } catch (UnsupportedEncodingException e) {
            throw new AlchemyApiException(e);
        }
    }

    private static void validateUrl(final String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new AlchemyApiException(e);
        }
    }

    private static void validateHtml(final String html) {
        if(isBlank(html)) { throw new AlchemyApiException("Html must not be blank"); }
    }

    private static void validateText(final String text) {
        if(isBlank(text)) { throw new AlchemyApiException("Text must not be blank"); }
    }

}
