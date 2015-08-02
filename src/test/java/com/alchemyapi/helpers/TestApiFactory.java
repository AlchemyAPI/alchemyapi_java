package com.alchemyapi.helpers;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.AlchemyApiConfiguration;
import com.alchemyapi.api.exceptions.AlchemyApiException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Created by kenny
 */
public class TestApiFactory {

    // load configuration file from external file
    public static AlchemyApi build(final File configurationFile) {
        try {
            final String apiKey = trimToEmpty(IOUtils.toString(new FileReader(configurationFile)));
            final AlchemyApiConfiguration configuration = new AlchemyApiConfiguration(apiKey);
            return new AlchemyApi(configuration);

        } catch (IOException e) {
            throw new AlchemyApiException(e);
        }
    }

}
