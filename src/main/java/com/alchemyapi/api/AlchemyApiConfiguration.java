package com.alchemyapi.api;

/**
 * Created by kenny
 */
public class AlchemyApiConfiguration {

    private String apiKey;

    private String apiSubDomain = "access";

    public AlchemyApiConfiguration(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSubDomain() {
        return apiSubDomain;
    }

    public void setApiSubDomain(final String apiSubDomain) {
        this.apiSubDomain = apiSubDomain;
    }

    @Override
    public String toString() {
        return "AlchemyApiConfiguration{" +
                "apiKey='" + apiKey + '\'' +
                ", apiSubDomain='" + apiSubDomain + '\'' +
                '}';
    }

}
