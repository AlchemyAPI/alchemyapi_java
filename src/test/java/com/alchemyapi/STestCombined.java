package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.CombinedParameters;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestCombined {

    private static final Logger LOGGER = Logger.getLogger(STestCombined.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetCombined("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void text() {
        final Document document = alchemyApi.textGetCombined(
                "Hello there, my name is Bob Jones. I live in the United States of America. Where do you live, Fred?");
        LOGGER.info(document);

        // Only extract entities & keywords
        final CombinedParameters combinedParams = new CombinedParameters();
        combinedParams.setSentiment(true);
        combinedParams.setExtract("entity");
        combinedParams.setExtract("keyword");
        final Document document2 = alchemyApi.textGetCombined("Madonna enjoys tasty Pepsi. I love her style.", combinedParams);
        LOGGER.info(document2);
    }

}
