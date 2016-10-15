package com.alchemyapi.api.exceptions;

/**
 * Created by kenny
 */
public class AlchemyApiException extends RuntimeException {

    public AlchemyApiException(final String message) {
        super(message);
    }

    public AlchemyApiException(final Exception e) {
        super(e.getMessage(), e);
    }

}
