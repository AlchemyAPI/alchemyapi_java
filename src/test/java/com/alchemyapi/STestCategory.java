package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.api.parameters.CategoryParameters;
import com.alchemyapi.api.parameters.Parameters;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestCategory {

    private static final Logger LOGGER = Logger.getLogger(STestCategory.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetCategory("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void text() {
        final Document document = alchemyApi.textGetCategory("Latest on the War in Iraq.");
        LOGGER.info(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetCategory(html, "http://www.test.com/");
        LOGGER.info(document);
    }

    @Test
    public void htmlRdfFormat() {
        final String html = ResourceUtils.toString("data/example.html");
        final CategoryParameters categoryParameters = new CategoryParameters();
        categoryParameters.setOutputMode(Parameters.OUTPUT_RDF);
        final Document document2 = alchemyApi.htmlGetCategory(html, "http://www.test.com/", categoryParameters);
        LOGGER.info(document2);
    }

}
