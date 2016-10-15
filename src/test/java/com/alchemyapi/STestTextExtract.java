package com.alchemyapi;

import com.alchemyapi.api.AlchemyApi;
import com.alchemyapi.helpers.ResourceUtils;
import com.alchemyapi.helpers.TestApiFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;

/**
 * Created by kenny
 */
public class STestTextExtract {

    private static final Logger LOGGER = Logger.getLogger(STestTextExtract.class);

    private final AlchemyApi alchemyApi = TestApiFactory.build(new File(System.getProperty("user.home"), ".alchemy/api.key"));

    /**
     * Extract page text from a web URL. Ignoring ads, navigation links,and other content.
     */
    @Test
    public void urlClean() {
        final Document document = alchemyApi.urlGetText("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    /**
     * Extract raw page text from a web URL. Including ads, navigation, and other content.
     */
    @Test
    public void urlRaw() {
        final Document document = alchemyApi.urlGetRawText("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    /**
     * Extract a title from a web URL.
     */
    @Test
    public void urlTitle() {
        final Document document = alchemyApi.urlGetTitle("http://www.techcrunch.com/");
        LOGGER.info(document);
    }

    /**
     * Extract page text from html. Ignoring ads, navigation links,and other content.
     */
    @Test
    public void htmlClean() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetText(html, "http://www.test.com/");
        LOGGER.info(document);
    }

    /**
     * Extract page text from html. Including ads, navigation links,and other content.
     */
    @Test
    public void htmlRaw() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetRawText(html, "http://www.test.com/");
        LOGGER.info(document);
    }

    /**
     * EExtract a title from html.
     */
    @Test
    public void htmlTitle() {
        final String html = ResourceUtils.toString("data/example.html");
        final Document document = alchemyApi.htmlGetTitle(html, "http://www.test.com/");
        LOGGER.info(document);
    }

}
