package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;


public class STestEmotion {

    private static final Logger LOGGER = Logger.getLogger(STestEmotion.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    @Test
    public void url() {
        final Document document = alchemyApi.urlGetEmotion("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    @Test
    public void text() {
        final Document document = alchemyApi.textGetEmotion(
                "This thing has a steering wheel, tires, and an engine. Do you know what it is?");
        LOGGER.info(document);
    }

    @Test
    public void html() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetEmotion(html, "http://www.test.com/");
        LOGGER.info(document);
    }

}
